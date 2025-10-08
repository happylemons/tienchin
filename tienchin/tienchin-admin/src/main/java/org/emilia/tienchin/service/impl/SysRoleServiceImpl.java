package org.emilia.tienchin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.emilia.tienchin.controller.dto.role.ListSysRoleReq;
import org.emilia.tienchin.mapper.SysRoleMapper;
import org.emilia.tienchin.pojo.entity.SysRole;
import org.emilia.tienchin.service.SysConfigService;
import org.emilia.tienchin.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> roles(Long userId) {
        return sysRoleMapper.selectByUserId(userId);
    }

    @Override
    public List<SysRole> selectRoles(ListSysRoleReq role) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        if (role != null) {
            if (role.getRoleName() != null) {
                String roleName = role.getRoleName().replaceAll("^\\s+", "");
                queryWrapper.like("role_name", roleName);
            }
            if (role.getRoleKey() != null) {
                queryWrapper.like("role_key", role.getRoleKey());
            }
            if (role.getStatus() != null) {
                queryWrapper.eq("status", role.getStatus());
            }
        }
        return sysRoleMapper.selectList(queryWrapper);
    }


}
