package org.emilia.tienchin.controller.system;

import java.util.List;
//import jakarta.servlet.http.HttpServletResponse;

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
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.emilia.tienchin.config.PageImpl;
import org.emilia.tienchin.controller.common.BaseController;
import org.emilia.tienchin.controller.dto.role.*;
import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.pojo.business.TableDataInfo;
import org.emilia.tienchin.pojo.entity.SysRole;
import org.emilia.tienchin.pojo.entity.SysUser;
import org.emilia.tienchin.pojo.sys.SysUserRole;
import org.emilia.tienchin.service.SysRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
//import org.emilia.tienchin.common.constant.UserConstants;
//import org.emilia.tienchin.web.controller.pojo.TableDataInfo;

/**
 * 角色信息
 *
 * @author tienchin
 */
@RestController
@RequestMapping("/system/role")
@RequiredArgsConstructor
public class SysRoleController extends BaseController {

    private final SysRoleService sysRoleService;

    //    @PreAuthorize("hasPermission('system:role:list')")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody @Validated ListSysRoleReq role, PageImpl<SysRole> page) {
        List<SysRole> result = sysRoleService.selectRoles(role, page);
        return getDataTable(result);
    }

    //    @Log(title = "角色管理", businessType = BusinessType.EXPORT)
//    @PreAuthorize("hasPermission('system:role:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        response.setContentType("text/csv;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"role_export.csv\"");
        sysRoleService.export();
    }

    /**
     * 根据角色编号获取详细信息
     */
//    @PreAuthorize("hasPermission('system:role:query')")
    @GetMapping(value = "/{roleId}")
    public AjaxResult getInfo(@PathVariable("roleId") Long roleId) {
        SysRole role = sysRoleService.selectRoleById(roleId);
        return AjaxResult.success(role);

    }

    /**
     * 新增角色
     */
//    @PreAuthorize("hasPermission('system:role:add')")
//    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody AddSysRoleReq role) {
        return sysRoleService.add(role);
    }

    /**
     * 修改保存角色
     */
//    @PreAuthorize("hasPermission('system:role:edit')")
//    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody EditSysRoleReq role) {
        return sysRoleService.edit(role);

    }

    /**
     * 修改保存数据权限
     */
//    @PreAuthorize("hasPermission('system:role:edit')")
//    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping("/dataScope")
    public AjaxResult dataScope(@RequestBody @Validated DataScopeSysRoleReq role) {
        return sysRoleService.dataScope(role);

    }

    /**
     * 状态修改
     */
//    @PreAuthorize("hasPermission('system:role:edit')")
//    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody @Validated ChangeStatusRoleReq role) {
        return sysRoleService.changeStatus(role);
    }

    /**
     * 删除角色
     */
//    @PreAuthorize("hasPermission('system:role:remove')")
//    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{roleIds}")
    public AjaxResult remove(@PathVariable("roleIds") Long[] roleIds) {
        return sysRoleService.remove(roleIds);
    }

    /**
     * 获取角色选择框列表
     */
//    @PreAuthorize("hasPermission('system:role:query')")
    @GetMapping("/optionselect")
    public AjaxResult optionselect() {
        return sysRoleService.optionselect();
    }

    /**
     * 查询已分配用户角色列表
     */
//    @PreAuthorize("hasPermission('system:role:list')")
    @GetMapping("/authUser/allocatedList")
    public TableDataInfo allocatedList(@RequestParam("roleId") Long roleId, PageImpl<SysUser> page) {
        List<SysUser> result = sysRoleService.allocatedList(roleId, page);
        return getDataTable(result);
    }

    /**
     * 查询未分配用户角色列表
     */
//    @PreAuthorize("hasPermission('system:role:list')")
    @GetMapping("/authUser/unallocatedList")
    public TableDataInfo unallocatedList(@RequestParam("roleId") Long roleId, PageImpl<SysUser> page) {
        List<SysUser> result = sysRoleService.unallocatedList(roleId, page);
        return getDataTable(result);
    }

    /**
     * 取消授权用户
     */
//    @PreAuthorize("hasPermission('system:role:edit')")
//    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PutMapping("/authUser/cancel")
    public AjaxResult cancelAuthUser(@RequestParam("roleId") Long roleId, @RequestParam("userId") Long userId) {
        return sysRoleService.cancelAuthUser(roleId, userId);
    }

    /**
     * 批量取消授权用户
     */
//    @PreAuthorize("hasPermission('system:role:edit')")
//    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PutMapping("/authUser/cancelAll")
    public AjaxResult cancelAuthUserAll(@RequestParam("roleId") Long roleId, @RequestParam("userIds") Long[] userIds) {
        return sysRoleService.cancelAuthUserAll(roleId, userIds);
    }

    /**
     * 批量选择用户授权
     */
//    @PreAuthorize("hasPermission('system:role:edit')")
//    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PutMapping("/authUser/selectAll")
    public AjaxResult selectAuthUserAll(@RequestParam("roleId") Long roleId, @RequestParam("userIds") Long[] userIds) {
        return sysRoleService.selectAuthUserAll(roleId, userIds);
    }
}
