package org.emilia.tienchin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.emilia.tienchin.pojo.entity.SysMenu;
import org.emilia.tienchin.pojo.entity.SysRole;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenu> {


    List<SysMenu> findAllMenus();
}
