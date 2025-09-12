package org.emilia.tienchin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.emilia.tienchin.mapper.SysUserMapper;
import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.pojo.entity.SysUser;
import org.emilia.tienchin.pojo.model.LoginBody;
import org.emilia.tienchin.service.SysLoginService;
import org.emilia.tienchin.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SysLoginServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysLoginService {

    @Autowired(required = false)
    private SysUserMapper sysUserMapper;

    @Override
    public AjaxResult login(LoginBody loginBody) {
        String uuid = loginBody.getUuid();
        String password = loginBody.getPassword();
        String code = loginBody.getCode();
        String username = loginBody.getUsername();

        if (StringUtils.isBlank(uuid) || StringUtils.isBlank(password) || StringUtils.isBlank(code) || StringUtils.isBlank(username)) {
            return AjaxResult.error("不能为空！！");
        }

        String token = JWTUtils.createToken(Long.parseLong(uuid));

        return null;
    }
}