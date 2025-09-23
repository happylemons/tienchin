package org.emilia.tienchin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.emilia.tienchin.pojo.entity.SysMenu;
import org.emilia.tienchin.pojo.entity.SysRole;
import org.emilia.tienchin.pojo.vo.RouterVo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SysMenuService extends IService<SysMenu> {
    List<RouterVo> getRouters();
}
