//package org.javaboy.tienchin.web.controller.system;
//
//import java.util.List;
//import java.util.Set;
//
//import org.javaboy.tienchin.web.controller.pojo.AjaxResult;
//import org.javaboy.tienchin.common.core.domain.entity.SysMenu;
//import org.javaboy.tienchin.common.core.domain.entity.SysUser;
//import org.javaboy.tienchin.common.core.domain.model.LoginBody;
//import org.javaboy.tienchin.common.utils.SecurityUtils;
//import org.javaboy.tienchin.framework.web.service.SysLoginService;
//import org.javaboy.tienchin.framework.web.service.SysPermissionService;
//import org.javaboy.tienchin.system.service.ISysMenuService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.javaboy.tienchin.common.constant.Constants;
//
///**
// * 登录验证
// *
// * @author tienchin
// */
//@RestController
//public class SysLoginController {
//    @Autowired
//    private SysLoginService loginService;
//
//    @Autowired
//    private ISysMenuService menuService;
//
//    @Autowired
//    private SysPermissionService permissionService;
//
//    /**
//     * 登录方法
//     *
//     * @param loginBody 登录信息
//     * @return 结果
//     */
//    @PostMapping("/login")
//    public AjaxResult login(@RequestBody LoginBody loginBody) {
//        return null;
//
//    }
//
//    /**
//     * 获取用户信息
//     *
//     * @return 用户信息
//     */
//    @GetMapping("getInfo")
//    public AjaxResult getInfo() {
//        return null;
//
//    }
//
//    /**
//     * 获取路由信息
//     *
//     * @return 路由信息
//     */
//    @GetMapping("getRouters")
//    public AjaxResult getRouters() {
//        return null;
//
//    }
//}
