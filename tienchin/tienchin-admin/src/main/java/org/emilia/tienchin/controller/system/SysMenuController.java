//package org.emilia.tienchin.web.controller.system;
//
//import java.util.List;
//
//import org.emilia.tienchin.common.annotation.Log;
//import org.emilia.tienchin.common.core.controller.BaseController;
//import org.emilia.tienchin.web.controller.pojo.AjaxResult;
//import org.emilia.tienchin.common.core.domain.entity.SysMenu;
//import org.emilia.tienchin.common.enums.BusinessType;
//import org.emilia.tienchin.common.utils.StringUtils;
//import org.emilia.tienchin.system.service.ISysMenuService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.emilia.tienchin.common.constant.UserConstants;
//
///**
// * 菜单信息
// *
// * @author tienchin
// */
//@RestController
//@RequestMapping("/system/menu")
//public class SysMenuController extends BaseController {
//    @Autowired
//    private ISysMenuService menuService;
//
//    /**
//     * 获取菜单列表
//     */
//    @PreAuthorize("hasPermission('system:menu:list')")
//    @GetMapping("/list")
//    public AjaxResult list(SysMenu menu) {
//        return null;
//
//    }
//
//    /**
//     * 根据菜单编号获取详细信息
//     */
//    @PreAuthorize("hasPermission('system:menu:query')")
//    @GetMapping(value = "/{menuId}")
//    public AjaxResult getInfo(@PathVariable Long menuId) {
//        return null;
//
//    }
//
//    /**
//     * 获取菜单下拉树列表
//     */
//    @GetMapping("/treeselect")
//    public AjaxResult treeselect(SysMenu menu) {
//        return null;
//
//    }
//
//    /**
//     * 加载对应角色菜单列表树
//     */
//    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
//    public AjaxResult roleMenuTreeselect(@PathVariable("roleId") Long roleId) {
//        return null;
//
//    }
//
//    /**
//     * 新增菜单
//     */
//    @PreAuthorize("hasPermission('system:menu:add')")
//    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@Validated @RequestBody SysMenu menu) {
//        return null;
//
//    }
//
//    /**
//     * 修改菜单
//     */
//    @PreAuthorize("hasPermission('system:menu:edit')")
//    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@Validated @RequestBody SysMenu menu) {
//        return null;
//
//    }
//
//    /**
//     * 删除菜单
//     */
//    @PreAuthorize("hasPermission('system:menu:remove')")
//    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{menuId}")
//    public AjaxResult remove(@PathVariable("menuId") Long menuId) {
//        return null;
//
//    }
//}