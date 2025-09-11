//package org.emilia.tienchin.web.controller.system;
//
//import org.emilia.tienchin.common.core.controller.BaseController;
//import org.emilia.tienchin.web.controller.pojo.AjaxResult;
//import org.emilia.tienchin.common.core.domain.model.RegisterBody;
//import org.emilia.tienchin.common.utils.StringUtils;
//import org.emilia.tienchin.system.service.ISysConfigService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.emilia.tienchin.framework.web.service.SysRegisterService;
//
///**
// * 注册验证
// *
// * @author tienchin
// */
//@RestController
//public class SysRegisterController extends BaseController {
//    @Autowired
//    private SysRegisterService registerService;
//
//    @Autowired
//    private ISysConfigService configService;
//
//    @PostMapping("/register")
//    public AjaxResult register(@RequestBody RegisterBody user) {
//        return null;
//
//    }
//}
