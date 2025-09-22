package org.emilia.tienchin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.emilia.tienchin.mapper.SysDeptMapper;
import org.emilia.tienchin.mapper.SysRoleMapper;
import org.emilia.tienchin.mapper.SysUserMapper;
import org.emilia.tienchin.pojo.entity.SysDept;
import org.emilia.tienchin.pojo.entity.SysUser;
import org.emilia.tienchin.pojo.model.LoginUser;
import org.emilia.tienchin.service.SysLoginService;
import org.emilia.tienchin.service.SysUserService;
import org.emilia.tienchin.utils.IpUtils;
import org.emilia.tienchin.utils.JwtUtils;
import org.emilia.tienchin.utils.UserAgentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;


@Service
public class SysLoginServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysLoginService {

    @Autowired(required = false)
    private SysUserService sysUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public String login(String username, String password, String code, String uuid, HttpServletRequest request) {
        // 验证码校验
        // validateCaptcha(username, code, uuid);

        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (Exception e) {
            throw new RuntimeException("用户名或密码错误");
        }

        //安全上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);


        // 生成token
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String token = createToken(loginUser, request);
        loginUser.setToken(token);
        SysUser user = sysUserService.findUser(username);

        redisTemplate.opsForValue().set("login_token:" + token, loginUser);
        redisTemplate.opsForValue().set("user", user);
        System.out.println(token);
        System.out.println(loginUser);
        return token;
    }

    @Override
    public LoginUser getInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        SysUser user = loginUser.getUser();
        SysDept dept = sysDeptMapper.selectByDeptId(loginUser.getDeptId());
        user.setDept(dept);
        return loginUser;
    }

    /**
     * 创建令牌
     */
    private String createToken(LoginUser loginUser, HttpServletRequest request) {
        // 更新用户登录信息
        SysUser user = loginUser.getUser();
        user.setLoginIp(IpUtils.getIpAddr(request));
        user.setLoginDate(new Date());
        sysUserService.updateUserProfile(user);

        // 设置登录信息
        loginUser.setIpaddr(IpUtils.getIpAddr(request));
        loginUser.setLoginLocation(IpUtils.getRealAddressByIP(loginUser.getIpaddr()));
        String userAgent = request.getHeader("User-Agent");
        loginUser.setBrowser(UserAgentUtils.getBrowserName(userAgent));
        loginUser.setOs(UserAgentUtils.getOsName(userAgent));
        loginUser.setLoginTime(System.currentTimeMillis());

        // 生成token
        String token = jwtUtils.generateToken(loginUser.getUsername());
        loginUser.setToken(token);

        // 可以将用户信息存入缓存
        // tokenService.setLoginUser(loginUser);

        return token;
    }

}