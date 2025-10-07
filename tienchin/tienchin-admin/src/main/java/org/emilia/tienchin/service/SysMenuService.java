package org.emilia.tienchin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.emilia.tienchin.controller.dto.menu.EditSysMenuReq;
import org.emilia.tienchin.controller.dto.menu.ListSysMenuReq;
import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.pojo.business.TreeSelect;
import org.emilia.tienchin.pojo.entity.SysMenu;
import org.emilia.tienchin.pojo.vo.RouterVo;

import java.util.List;


public interface SysMenuService extends IService<SysMenu> {

    List<RouterVo> getRouters();

    SysMenu selectByMenuId(Long menuId);

    List<SysMenu> selectMenus(ListSysMenuReq menu);

    AjaxResult add(SysMenu menu);

    AjaxResult edit(EditSysMenuReq menu);

    AjaxResult removeByMenuId(Long menuId);

    AjaxResult treeselect(SysMenu menu,Long userId);

    List<TreeSelect> selectMenusByUserId(Long userId);

    List<Long> checkedKeys(Long roleId);
}
