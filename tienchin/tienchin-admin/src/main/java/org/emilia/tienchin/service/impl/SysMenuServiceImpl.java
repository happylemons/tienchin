package org.emilia.tienchin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.emilia.tienchin.mapper.SysMenuMapper;
import org.emilia.tienchin.pojo.entity.SysMenu;
import org.emilia.tienchin.pojo.entity.SysRole;
import org.emilia.tienchin.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

}
