package org.emilia.tienchin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.emilia.tienchin.mapper.SysUserMapper;
import org.emilia.tienchin.pojo.entity.SysUser;
import org.emilia.tienchin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findUser(String username) {
        return sysUserMapper.findUser(username);
    }

    @Override
    public void updateUserProfile(SysUser user) {

    }
}