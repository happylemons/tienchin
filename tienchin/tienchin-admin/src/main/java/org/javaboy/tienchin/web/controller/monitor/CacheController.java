//package org.javaboy.tienchin.web.controller.monitor;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//
//import org.javaboy.tienchin.web.controller.pojo.AjaxResult;
//import org.javaboy.tienchin.common.utils.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 缓存监控
// *
// * @author tienchin
// */
//@RestController
//@RequestMapping("/monitor/cache")
//public class CacheController {
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//
//    @PreAuthorize("hasPermission('monitor:cache:list')")
//    @GetMapping()
//    public AjaxResult getInfo() throws Exception {
//
//        return null;
//    }
//}
