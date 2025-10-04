package org.emilia.tienchin.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

//import org.emilia.tienchin.common.annotation.Log;
//import org.emilia.tienchin.common.core.controller.BaseController;
//import org.emilia.tienchin.web.controller.pojo.AjaxResult;
//import org.emilia.tienchin.common.core.domain.entity.SysRole;
//import org.emilia.tienchin.common.core.domain.entity.SysUser;
//import org.emilia.tienchin.common.core.domain.model.LoginUser;
//import org.emilia.tienchin.common.enums.BusinessType;
//import org.emilia.tienchin.common.utils.StringUtils;
//import org.emilia.tienchin.common.utils.poi.ExcelUtil;
//import org.emilia.tienchin.framework.web.service.SysPermissionService;
//import org.emilia.tienchin.framework.web.service.TokenService;
//import org.emilia.tienchin.system.domain.SysUserRole;
//import org.emilia.tienchin.system.service.ISysRoleService;
//import org.emilia.tienchin.system.service.ISysUserService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.emilia.tienchin.controller.common.BaseController;
import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.pojo.business.TableDataInfo;
import org.emilia.tienchin.pojo.entity.SysRole;
import org.emilia.tienchin.pojo.entity.SysUser;
import org.emilia.tienchin.pojo.sys.SysUserRole;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.emilia.tienchin.common.constant.UserConstants;
//import org.emilia.tienchin.web.controller.pojo.TableDataInfo;

/**
 * 角色信息
 *
 * @author tienchin
 */
@RestController
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {


//    @PreAuthorize("hasPermission('system:role:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysRole role) {
        return null;

    }

//    @Log(title = "角色管理", businessType = BusinessType.EXPORT)
//    @PreAuthorize("hasPermission('system:role:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysRole role) {

    }

    /**
     * 根据角色编号获取详细信息
     */
//    @PreAuthorize("hasPermission('system:role:query')")
    @GetMapping(value = "/{roleId}")
    public AjaxResult getInfo(@PathVariable Long roleId) {
        return null;

    }

    /**
     * 新增角色
     */
//    @PreAuthorize("hasPermission('system:role:add')")
//    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysRole role) {
        return null;


    }

    /**
     * 修改保存角色
     */
//    @PreAuthorize("hasPermission('system:role:edit')")
//    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysRole role) {
        return null;

    }

    /**
     * 修改保存数据权限
     */
//    @PreAuthorize("hasPermission('system:role:edit')")
//    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping("/dataScope")
    public AjaxResult dataScope(@RequestBody SysRole role) {
        return null;

    }

    /**
     * 状态修改
     */
//    @PreAuthorize("hasPermission('system:role:edit')")
//    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysRole role) {
        return null;

    }

    /**
     * 删除角色
     */
//    @PreAuthorize("hasPermission('system:role:remove')")
//    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{roleIds}")
    public AjaxResult remove(@PathVariable Long[] roleIds) {
        return null;

    }

    /**
     * 获取角色选择框列表
     */
//    @PreAuthorize("hasPermission('system:role:query')")
    @GetMapping("/optionselect")
    public AjaxResult optionselect() {
        return null;

    }

    /**
     * 查询已分配用户角色列表
     */
//    @PreAuthorize("hasPermission('system:role:list')")
    @GetMapping("/authUser/allocatedList")
    public TableDataInfo allocatedList(SysUser user) {
        return null;

    }

    /**
     * 查询未分配用户角色列表
     */
//    @PreAuthorize("hasPermission('system:role:list')")
    @GetMapping("/authUser/unallocatedList")
    public TableDataInfo unallocatedList(SysUser user) {
        return null;

    }

    /**
     * 取消授权用户
     */
//    @PreAuthorize("hasPermission('system:role:edit')")
//    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PutMapping("/authUser/cancel")
    public AjaxResult cancelAuthUser(@RequestBody SysUserRole userRole) {
        return null;

    }

    /**
     * 批量取消授权用户
     */
//    @PreAuthorize("hasPermission('system:role:edit')")
//    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PutMapping("/authUser/cancelAll")
    public AjaxResult cancelAuthUserAll(Long roleId, Long[] userIds) {
        return null;

    }

    /**
     * 批量选择用户授权
     */
//    @PreAuthorize("hasPermission('system:role:edit')")
//    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PutMapping("/authUser/selectAll")
    public AjaxResult selectAuthUserAll(Long roleId, Long[] userIds) {
        return null;

    }
}
