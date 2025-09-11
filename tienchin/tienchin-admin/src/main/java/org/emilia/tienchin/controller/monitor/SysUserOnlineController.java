//package org.emilia.tienchin.web.controller.monitor;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//
//import org.emilia.tienchin.common.annotation.Log;
//import org.emilia.tienchin.common.core.controller.BaseController;
//import org.emilia.tienchin.web.controller.pojo.AjaxResult;
//import org.emilia.tienchin.common.core.domain.model.LoginUser;
//import org.emilia.tienchin.common.core.redis.RedisCache;
//import org.emilia.tienchin.common.enums.BusinessType;
//import org.emilia.tienchin.common.utils.StringUtils;
//import org.emilia.tienchin.system.domain.SysUserOnline;
//import org.emilia.tienchin.system.service.ISysUserOnlineService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.emilia.tienchin.common.constant.Constants;
//import org.emilia.tienchin.web.controller.pojo.TableDataInfo;
//
///**
// * 在线用户监控
// *
// * @author tienchin
// */
//@RestController
//@RequestMapping("/monitor/online")
//public class SysUserOnlineController extends BaseController {
//    @Autowired
//    private ISysUserOnlineService userOnlineService;
//
//    @Autowired
//    private RedisCache redisCache;
//
//    @PreAuthorize("hasPermission('monitor:online:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(String ipaddr, String userName) {
//        return null;
//
//    }
//
//    /**
//     * 强退用户
//     */
//    @PreAuthorize("hasPermission('monitor:online:forceLogout')")
//    @Log(title = "在线用户", businessType = BusinessType.FORCE)
//    @DeleteMapping("/{tokenId}")
//    public AjaxResult forceLogout(@PathVariable String tokenId) {
//        return null;
//
//    }
//}
