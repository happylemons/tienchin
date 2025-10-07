//package org.emilia.tienchin.web.controller.system;
//
//import java.util.List;
//import jakarta.servlet.http.HttpServletResponse;
//
//import org.emilia.tienchin.common.annotation.Log;
//import org.emilia.tienchin.common.core.controller.BaseController;
//import org.emilia.tienchin.web.controller.pojo.AjaxResult;
//import org.emilia.tienchin.common.enums.BusinessType;
//import org.emilia.tienchin.common.utils.poi.ExcelUtil;
//import org.emilia.tienchin.system.domain.SysPost;
//import org.emilia.tienchin.system.service.ISysPostService;
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
//import org.emilia.tienchin.common.constant.UserConstants;
//import org.emilia.tienchin.web.controller.pojo.TableDataInfo;
//
///**
// * 岗位信息操作处理
// *
// * @author tienchin
// */
//@RestController
//@RequestMapping("/system/post")
//public class SysPostController extends BaseController {
//    @Autowired
//    private ISysPostService postService;
//
//    /**
//     * 获取岗位列表
//     */
//    @PreAuthorize("hasPermission('system:post:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(SysPost post) {
//        return null;
//
//    }
//
//    @Log(title = "岗位管理", businessType = BusinessType.EXPORT)
//    @PreAuthorize("hasPermission('system:post:export')")
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, SysPost post) {
//
//
//    }
//
//    /**
//     * 根据岗位编号获取详细信息
//     */
//    @PreAuthorize("hasPermission('system:post:query')")
//    @GetMapping(value = "/{postId}")
//    public AjaxResult getInfo(@PathVariable Long postId) {
//        return null;
//
//    }
//
//    /**
//     * 新增岗位
//     */
//    @PreAuthorize("hasPermission('system:post:add')")
//    @Log(title = "岗位管理", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@Validated @RequestBody SysPost post) {
//        return null;
//
//    }
//
//    /**
//     * 修改岗位
//     */
//    @PreAuthorize("hasPermission('system:post:edit')")
//    @Log(title = "岗位管理", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@Validated @RequestBody SysPost post) {
//        return null;
//
//    }
//
//    /**
//     * 删除岗位
//     */
//    @PreAuthorize("hasPermission('system:post:remove')")
//    @Log(title = "岗位管理", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{postIds}")
//    public AjaxResult remove(@PathVariable Long[] postIds) {
//        return null;
//
//    }
//
//    /**
//     * 获取岗位选择框列表
//     */
//    @GetMapping("/optionselect")
//    public AjaxResult optionselect() {
//        return null;
//
//    }
//}
