package org.emilia.tienchin.controller.system;


import org.emilia.tienchin.pojo.entity.SysMenu;
import org.emilia.tienchin.pojo.entity.SysRole;
import org.emilia.tienchin.pojo.entity.SysUser;
import org.emilia.tienchin.pojo.model.LoginBody;
import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.pojo.model.LoginUser;
import org.emilia.tienchin.pojo.vo.RouterVo;
import org.emilia.tienchin.service.SysLoginService;

import org.emilia.tienchin.service.SysMenuService;
import org.emilia.tienchin.service.SysPermissionService;
import org.emilia.tienchin.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 登录验证
 *
 * @author tienchin
 */
@RestController
public class SysLoginController {

    @Autowired(required = false)
    private SysLoginService sysLoginService;

    @Autowired(required = false)
    private SysMenuService menuService;

    @Autowired(required = false)
    private SysPermissionService permissionService;

    @Autowired(required = false)
    private SysRoleService roleService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody, HttpServletRequest request) {
        String token = sysLoginService.login(loginBody.getUsername(), loginBody.getPassword(),
                loginBody.getCode(), loginBody.getUuid(), request);
        return AjaxResult.success().put("token", token);
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        LoginUser loginUser = sysLoginService.getInfo();
        AjaxResult result = new AjaxResult();
//        result.put("permissions", permissionService.hasPermission(user.getUserId()));
        Set<String> permissions = new HashSet<>();
        permissions.add("*:*:*");
        result.put("permissions", permissions);
        result.put("roles", roleService.roles(loginUser.getUserId()));
        result.put("user", loginUser.getUser());
        return result;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters() {
//        AjaxResult result = new AjaxResult();
        List<RouterVo> routers = menuService.getRouters();
//        result.put("routers", routers);
        return AjaxResult.success(routers);

    }
}
