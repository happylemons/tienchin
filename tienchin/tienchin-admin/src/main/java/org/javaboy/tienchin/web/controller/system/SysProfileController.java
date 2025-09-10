//package org.javaboy.tienchin.web.controller.system;
//
//import java.io.IOException;
//
//import org.javaboy.tienchin.common.annotation.Log;
//import org.javaboy.tienchin.common.core.controller.BaseController;
//import org.javaboy.tienchin.web.controller.pojo.AjaxResult;
//import org.javaboy.tienchin.common.core.domain.entity.SysUser;
//import org.javaboy.tienchin.common.core.domain.model.LoginUser;
//import org.javaboy.tienchin.common.enums.BusinessType;
//import org.javaboy.tienchin.common.utils.SecurityUtils;
//import org.javaboy.tienchin.common.utils.StringUtils;
//import org.javaboy.tienchin.common.utils.file.FileUploadUtils;
//import org.javaboy.tienchin.system.service.ISysUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//import org.javaboy.tienchin.common.config.TienChinConfig;
//import org.javaboy.tienchin.common.constant.UserConstants;
//import org.javaboy.tienchin.framework.web.service.TokenService;
//
///**
// * 个人信息 业务处理
// *
// * @author tienchin
// */
//@RestController
//@RequestMapping("/system/user/profile")
//public class SysProfileController extends BaseController {
//    @Autowired
//    private ISysUserService userService;
//
//    @Autowired
//    private TokenService tokenService;
//
//    /**
//     * 个人信息
//     */
//    @GetMapping
//    public AjaxResult profile() {
//        return null;
//
//    }
//
//    /**
//     * 修改用户
//     */
//    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult updateProfile(@RequestBody SysUser user) {
//        return null;
//
//    }
//
//    /**
//     * 重置密码
//     */
//    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
//    @PutMapping("/updatePwd")
//    public AjaxResult updatePwd(String oldPassword, String newPassword) {
//        return null;
//
//    }
//
//    /**
//     * 头像上传
//     */
//    @Log(title = "用户头像", businessType = BusinessType.UPDATE)
//    @PostMapping("/avatar")
//    public AjaxResult avatar(@RequestParam("avatarfile") MultipartFile file) throws IOException {
//        return null;
//
//    }
//}
