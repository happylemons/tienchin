package org.emilia.tienchin.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.emilia.tienchin.mapper.SysRoleMapper;
import org.emilia.tienchin.pojo.entity.SysRole;
import org.emilia.tienchin.service.SysConfigService;
import org.emilia.tienchin.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper,SysRole> implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> roles(Long userId) {
        return   sysRoleMapper.selectByUserId(userId);
    }
}
