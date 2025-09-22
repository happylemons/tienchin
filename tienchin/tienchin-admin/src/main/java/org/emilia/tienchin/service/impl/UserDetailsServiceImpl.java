package org.emilia.tienchin.service.impl;

import org.emilia.tienchin.pojo.entity.SysUser;
import org.emilia.tienchin.pojo.model.LoginUser;
import org.emilia.tienchin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.findUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        if ("1".equals(user.getStatus())) {
            throw new UsernameNotFoundException("账号已停用");
        }
        if ("2".equals(user.getDelFlag())) {
            throw new UsernameNotFoundException("账号已删除");
        }

        return createLoginUser(user);
    }

    public LoginUser createLoginUser(SysUser user) {
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(user.getUserId());
        loginUser.setDeptId(user.getDeptId());
        loginUser.setUser(user);
        // 可以在这里设置用户的权限信息
        // loginUser.setPermissions(permissionService.getMenuPermission(user));
        return loginUser;
    }
}

