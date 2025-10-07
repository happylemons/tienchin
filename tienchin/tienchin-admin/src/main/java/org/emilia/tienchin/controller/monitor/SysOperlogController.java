//package org.emilia.tienchin.web.controller.monitor;
//
//import java.util.List;
//import jakarta.servlet.http.HttpServletResponse;
//
//import org.emilia.tienchin.common.annotation.Log;
//import org.emilia.tienchin.common.core.controller.BaseController;
//import org.emilia.tienchin.web.controller.pojo.AjaxResult;
//import org.emilia.tienchin.common.enums.BusinessType;
//import org.emilia.tienchin.common.utils.poi.ExcelUtil;
//import org.emilia.tienchin.web.controller.pojo.SysOperLog;
//import org.emilia.tienchin.system.service.ISysOperLogService;
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
// * 操作日志记录
// *
// * @author tienchin
// */
//@RestController
//@RequestMapping("/monitor/operlog")
//public class SysOperlogController extends BaseController {
//    @Autowired
//    private ISysOperLogService operLogService;
//
//    @PreAuthorize("hasPermission('monitor:operlog:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(SysOperLog operLog) {
//        return null;
//
//    }
//
//    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
//    @PreAuthorize("hasPermission('monitor:operlog:export')")
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, SysOperLog operLog) {
//
//    }
//
//    @Log(title = "操作日志", businessType = BusinessType.DELETE)
//    @PreAuthorize("hasPermission('monitor:operlog:remove')")
//    @DeleteMapping("/{operIds}")
//    public AjaxResult remove(@PathVariable Long[] operIds) {
//        return null;
//
//    }
//
//    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
//    @PreAuthorize("hasPermission('monitor:operlog:remove')")
//    @DeleteMapping("/clean")
//    public AjaxResult clean() {
//        return null;
//
//    }
//}
