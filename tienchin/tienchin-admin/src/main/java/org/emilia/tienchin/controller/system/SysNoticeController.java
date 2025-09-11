//package org.emilia.tienchin.web.controller.system;
//
//import java.util.List;
//
//import org.emilia.tienchin.common.core.controller.BaseController;
//import org.emilia.tienchin.web.controller.pojo.AjaxResult;
//import org.emilia.tienchin.common.enums.BusinessType;
//import org.emilia.tienchin.system.domain.SysNotice;
//import org.emilia.tienchin.system.service.ISysNoticeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.emilia.tienchin.common.annotation.Log;
//import org.emilia.tienchin.web.controller.pojo.TableDataInfo;
//
///**
// * 公告 信息操作处理
// *
// * @author tienchin
// */
//@RestController
//@RequestMapping("/system/notice")
//public class SysNoticeController extends BaseController {
//    @Autowired
//    private ISysNoticeService noticeService;
//
//    /**
//     * 获取通知公告列表
//     */
//    @PreAuthorize("hasPermission('system:notice:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(SysNotice notice) {
//        return null;
//
//    }
//
//    /**
//     * 根据通知公告编号获取详细信息
//     */
//    @PreAuthorize("hasPermission('system:notice:query')")
//    @GetMapping(value = "/{noticeId}")
//    public AjaxResult getInfo(@PathVariable Long noticeId) {
//        return null;
//
//    }
//
//    /**
//     * 新增通知公告
//     */
//    @PreAuthorize("hasPermission('system:notice:add')")
//    @Log(title = "通知公告", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@Validated @RequestBody SysNotice notice) {
//        return null;
//
//    }
//
//    /**
//     * 修改通知公告
//     */
//    @PreAuthorize("hasPermission('system:notice:edit')")
//    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@Validated @RequestBody SysNotice notice) {
//        return null;
//
//    }
//
//    /**
//     * 删除通知公告
//     */
//    @PreAuthorize("hasPermission('system:notice:remove')")
//    @Log(title = "通知公告", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{noticeIds}")
//    public AjaxResult remove(@PathVariable Long[] noticeIds) {
//        return null;
//
//    }
//}
