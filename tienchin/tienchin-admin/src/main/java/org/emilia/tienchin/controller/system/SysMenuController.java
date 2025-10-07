package org.emilia.tienchin.controller.system;

import org.emilia.tienchin.controller.common.BaseController;
import org.emilia.tienchin.controller.dto.menu.EditSysMenuReq;
import org.emilia.tienchin.controller.dto.menu.ListSysMenuReq;
import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.pojo.business.TreeSelect;
import org.emilia.tienchin.pojo.entity.SysMenu;
import org.emilia.tienchin.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单信息
 *
 * @author tienchin
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController {
    @Autowired(required = false)
    private SysMenuService menuService;

    /**
     * 获取菜单列表
     */
//    @PreAuthorize("hasPermission('system:menu:list')")
    @GetMapping("/list")
    public AjaxResult list(ListSysMenuReq menu) {
        List<SysMenu> result = menuService.selectMenus(menu);
        return AjaxResult.success(result);
    }

    /**
     * 根据菜单编号获取详细信息
     */
//    @PreAuthorize("hasPermission('system:menu:query')")
    @GetMapping(value = "/{menuId}")
    public AjaxResult getInfo(@PathVariable Long menuId) {
        SysMenu menu = menuService.selectByMenuId(menuId);
        return AjaxResult.success(menu);

    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(SysMenu menu) {
        Long userId = getUserId();
        if (userId == null) {
            return AjaxResult.error("获取用户id错误");
        }
        return menuService.treeselect(menu, userId);
    }

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public AjaxResult roleMenuTreeselect(@PathVariable("roleId") Long roleId) {
//        return menuService.roleMenuTreeselct(roleId);
        Long userId = getUserId();
        if (userId == null) {
            return AjaxResult.error("获取用户id错误");
        }
        AjaxResult result = new AjaxResult();
        List<TreeSelect> menus = menuService.selectMenusByUserId(userId);
        result.put("menus", menus);
        List<Long> keys = menuService.checkedKeys(roleId);
        if (keys== null || keys.size() == 0) {
            return AjaxResult.error("role id不存在");
        }
        result.put("checkedKeys", keys);
        return result;
    }

    /**
     * 新增菜单
     */
//    @PreAuthorize("hasPermission('system:menu:add')")
//    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysMenu menu) {
        return menuService.add(menu);
    }

    /**
     * 修改菜单
     */
//    @PreAuthorize("hasPermission('system:menu:edit')")
//    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody EditSysMenuReq menu) {
        return menuService.edit(menu);
    }

    /**
     * 删除菜单
     */
//    @PreAuthorize("hasPermission('system:menu:remove')")
//    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{menuId}")
    public AjaxResult remove(@PathVariable("menuId") Long menuId) {
        return menuService.removeByMenuId(menuId);
    }
}