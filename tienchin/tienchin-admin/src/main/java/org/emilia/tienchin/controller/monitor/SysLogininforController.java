//package org.emilia.tienchin.web.controller.monitor;
//
//import jakarta.servlet.http.HttpServletResponse;
//
//import org.emilia.tienchin.common.annotation.Log;
//import org.emilia.tienchin.common.core.controller.BaseController;
//import org.emilia.tienchin.web.controller.pojo.AjaxResult;
//import org.emilia.tienchin.common.enums.BusinessType;
//import org.emilia.tienchin.web.controller.pojo.SysLogininfor;
//import org.emilia.tienchin.system.service.ISysLogininforService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.emilia.tienchin.web.controller.pojo.TableDataInfo;
//
///**
// * 系统访问记录
// *
// * @author tienchin
// */
//@RestController
//@RequestMapping("/monitor/logininfor")
//public class SysLogininforController extends BaseController {
//    @Autowired
//    private ISysLogininforService logininforService;
//
//    @PreAuthorize("hasPermission('monitor:logininfor:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(SysLogininfor logininfor) {
//        return null;
//    }
//
//    @Log(title = "登录日志", businessType = BusinessType.EXPORT)
//    @PreAuthorize("hasPermission('monitor:logininfor:export')")
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, SysLogininfor logininfor) {
//    }
//
//    @PreAuthorize("hasPermission('monitor:logininfor:remove')")
//    @Log(title = "登录日志", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{infoIds}")
//    public AjaxResult remove(@PathVariable Long[] infoIds) {
//        return null;
//    }
//
//    @PreAuthorize("hasPermission('monitor:logininfor:remove')")
//    @Log(title = "登录日志", businessType = BusinessType.CLEAN)
//    @DeleteMapping("/clean")
//    public AjaxResult clean() {
//        return null;
//    }
//}
