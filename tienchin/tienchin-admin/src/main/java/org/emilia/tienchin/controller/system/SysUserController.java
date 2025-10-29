package org.emilia.tienchin.controller.system;


import lombok.RequiredArgsConstructor;
import org.emilia.tienchin.config.PageImpl;
import org.emilia.tienchin.controller.common.BaseController;
import org.emilia.tienchin.controller.dto.user.*;
import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.pojo.business.TableDataInfo;
import org.emilia.tienchin.pojo.entity.SysRole;
import org.emilia.tienchin.pojo.entity.SysUser;
import org.emilia.tienchin.pojo.sys.SysPost;
import org.emilia.tienchin.service.SysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 用户信息
 *
 * @author tienchin
 */
@RestController
@RequestMapping("/system/user")
@RequiredArgsConstructor
public class SysUserController extends BaseController {

    private final SysUserService sysUserService;

    /**
     * 获取用户列表
     */
//    @PreAuthorize("hasPermission('system:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody ListSysUserReq user, PageImpl<SysUser> page) {
        List<SysUser> result = sysUserService.selectUsers(user, page);
        return getDataTable(result);
    }

    //@Log(title = "用户管理", businessType = BusinessType.EXPORT)
//    @PreAuthorize("hasPermission('system:user:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        response.setContentType("text/csv;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"user_export.csv\"");
        sysUserService.export();
    }

    //@Log(title = "用户管理", businessType = BusinessType.IMPORT)
//    @PreAuthorize("hasPermission('system:user:import')")
    @PostMapping("/importData")
    public AjaxResult importData(@RequestParam("file") MultipartFile file,@RequestParam("updateSupport")  boolean updateSupport) throws Exception {
        return sysUserService.importData(file, updateSupport);
    }

    /**
     * 根据用户编号获取详细信息
     */
//    @PreAuthorize("hasPermission('system:user:query')")
    @GetMapping(value = {"/", "/{userId}"})
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId) {
        AjaxResult result = AjaxResult.success();
        List<SysRole> roles = sysUserService.selectRoles();
        List<SysPost> posts = sysUserService.selectPosts();
        boolean isAdmin = sysUserService.validateAdmin(userId);
        result.put("roles", isAdmin ? roles : roles.stream().filter(u -> u.getRoleId() != 1).collect(Collectors.toList()));
        result.put("posts", posts);
        if (userId != null) {
            SysUser user = sysUserService.selectByUserId(userId);
            result.put("roleIds", user.getRoleIds());
            result.put("postIds", user.getPostIds());
            result.put("data", user);
        }
        return result;
    }

    /**
     * 新增用户
     */
//    @PreAuthorize("hasPermission('system:user:add')")
//    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody AddSysUserReq user) {
        return sysUserService.add(user);
    }

    /**
     * 修改用户
     */
//    @PreAuthorize("hasPermission('system:user:edit')")
//    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody EditSysUserReq user) {
        return sysUserService.edit(user);
    }

    /**
     * 删除用户
     */
//    @PreAuthorize("hasPermission('system:user:remove')")
//    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable(value = "userIds") Long[] userIds) {
        return sysUserService.remove(userIds);
    }

    /**
     * 重置密码
     */
//    @PreAuthorize("hasPermission('system:user:resetPwd')")
//    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestParam(value = "userId") Long userId, @RequestParam(value = "password") String password) {
        return sysUserService.resetPwd(userId, password);
    }

    /**
     * 状态修改
     */
//    @PreAuthorize("hasPermission('system:user:edit')")
//    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody @Validated ChangeStatusUserReq user) {
        return sysUserService.changeStatus(user);
    }

    /**
     * 根据用户编号获取授权角色
     */
//    @PreAuthorize("hasPermission('system:user:query')")
    @GetMapping("/authRole/{userId}")
    public AjaxResult authRole(@PathVariable("userId") Long userId) {
        List<SysRole> sysRoles = sysUserService.authRole(userId);
        SysUser user = sysUserService.selectByUserId(userId);
        AjaxResult result = AjaxResult.success();
        result.put("roles", sysRoles);
        result.put("user", user);
        return result;
    }

    /**
     * 用户授权角色
     */
//    @PreAuthorize("hasPermission('system:user:edit')")
//    @Log(title = "用户管理", businessType = BusinessType.GRANT)
    @PutMapping("/authRole")
    public AjaxResult insertAuthRole(@RequestParam(value = "userId") Long userId, @RequestParam(value = "roleIds") Long[] roleIds) {
        return sysUserService.insertAuthRole(userId, roleIds);
    }
}
