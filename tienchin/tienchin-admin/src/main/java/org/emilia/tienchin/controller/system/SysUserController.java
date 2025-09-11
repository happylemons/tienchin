//package org.emilia.tienchin.web.controller.system;
//
//
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
//import org.emilia.tienchin.web.controller.domain.entity.SysUser;
//import org.emilia.tienchin.web.controller.pojo.AjaxResult;
//import org.emilia.tienchin.web.controller.pojo.TableDataInfo;
//import org.emilia.tienchin.web.enums.BusinessType;
//import org.springframework.validation.annotation.Validated;
//
//
///**
// * 用户信息
// *
// * @author tienchin
// */
//@RestController
//@RequestMapping("/system/user")
//public class SysUserController extends BaseController {
//
//
//    /**
//     * 获取用户列表
//     */
//    @PreAuthorize("hasPermission('system:user:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(SysUser user) {
//
//        return null;
//
//    }
//
//    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
//    @PreAuthorize("hasPermission('system:user:export')")
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, SysUser user) {
//
//
//    }
//
//    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
//    @PreAuthorize("hasPermission('system:user:import')")
//    @PostMapping("/importData")
//    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
//        return null;
//    }
//
//    @PostMapping("/importTemplate")
//    public void importTemplate(HttpServletResponse response) {
//
//
//    }
//
//    /**
//     * 根据用户编号获取详细信息
//     */
//    @PreAuthorize("hasPermission('system:user:query')")
//    @GetMapping(value = {"/", "/{userId}"})
//    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId) {
//        return null;
//
//    }
//
//    /**
//     * 新增用户
//     */
//    @PreAuthorize("hasPermission('system:user:add')")
//    @Log(title = "用户管理", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@Validated @RequestBody SysUser user) {
//        return null;
//
//    }
//
//    /**
//     * 修改用户
//     */
//    @PreAuthorize("hasPermission('system:user:edit')")
//    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@Validated @RequestBody SysUser user) {
//        return null;
//
//    }
//
//    /**
//     * 删除用户
//     */
//    @PreAuthorize("hasPermission('system:user:remove')")
//    @Log(title = "用户管理", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{userIds}")
//    public AjaxResult remove(@PathVariable Long[] userIds) {
//        return null;
//
//    }
//
//    /**
//     * 重置密码
//     */
//    @PreAuthorize("hasPermission('system:user:resetPwd')")
//    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
//    @PutMapping("/resetPwd")
//    public AjaxResult resetPwd(@RequestBody SysUser user) {
//        return null;
//
//    }
//
//    /**
//     * 状态修改
//     */
//    @PreAuthorize("hasPermission('system:user:edit')")
//    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
//    @PutMapping("/changeStatus")
//    public AjaxResult changeStatus(@RequestBody SysUser user) {
//        return null;
//
//    }
//
//    /**
//     * 根据用户编号获取授权角色
//     */
//    @PreAuthorize("hasPermission('system:user:query')")
//    @GetMapping("/authRole/{userId}")
//    public AjaxResult authRole(@PathVariable("userId") Long userId) {
//        return null;
//
//    }
//
//    /**
//     * 用户授权角色
//     */
//    @PreAuthorize("hasPermission('system:user:edit')")
//    @Log(title = "用户管理", businessType = BusinessType.GRANT)
//    @PutMapping("/authRole")
//    public AjaxResult insertAuthRole(Long userId, Long[] roleIds) {
//        return null;
//
//    }
//}
