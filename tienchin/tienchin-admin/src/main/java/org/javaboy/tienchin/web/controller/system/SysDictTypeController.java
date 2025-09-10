//package org.javaboy.tienchin.web.controller.system;
//
//import java.util.List;
//import javax.servlet.http.HttpServletResponse;
//
//import org.javaboy.tienchin.common.annotation.Log;
//import org.javaboy.tienchin.common.core.controller.BaseController;
//import org.javaboy.tienchin.web.controller.pojo.AjaxResult;
//import org.javaboy.tienchin.common.core.domain.entity.SysDictType;
//import org.javaboy.tienchin.common.enums.BusinessType;
//import org.javaboy.tienchin.common.utils.poi.ExcelUtil;
//import org.javaboy.tienchin.system.service.ISysDictTypeService;
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
//import org.javaboy.tienchin.common.constant.UserConstants;
//import org.javaboy.tienchin.web.controller.pojo.TableDataInfo;
//
///**
// * 数据字典信息
// *
// * @author tienchin
// */
//@RestController
//@RequestMapping("/system/dict/type")
//public class SysDictTypeController extends BaseController {
//    @Autowired
//    private ISysDictTypeService dictTypeService;
//
//    @PreAuthorize("hasPermission('system:dict:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(SysDictType dictType) {
//        return null;
//
//    }
//
//    @Log(title = "字典类型", businessType = BusinessType.EXPORT)
//    @PreAuthorize("hasPermission('system:dict:export')")
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, SysDictType dictType) {
//
//    }
//
//    /**
//     * 查询字典类型详细
//     */
//    @PreAuthorize("hasPermission('system:dict:query')")
//    @GetMapping(value = "/{dictId}")
//    public AjaxResult getInfo(@PathVariable Long dictId) {
//        return null;
//    }
//
//    /**
//     * 新增字典类型
//     */
//    @PreAuthorize("hasPermission('system:dict:add')")
//    @Log(title = "字典类型", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@Validated @RequestBody SysDictType dict) {
//        return null;
//
//    }
//
//    /**
//     * 修改字典类型
//     */
//    @PreAuthorize("hasPermission('system:dict:edit')")
//    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@Validated @RequestBody SysDictType dict) {
//        return null;
//
//    }
//
//    /**
//     * 删除字典类型
//     */
//    @PreAuthorize("hasPermission('system:dict:remove')")
//    @Log(title = "字典类型", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{dictIds}")
//    public AjaxResult remove(@PathVariable Long[] dictIds) {
//        return null;
//
//    }
//
//    /**
//     * 刷新字典缓存
//     */
//    @PreAuthorize("hasPermission('system:dict:remove')")
//    @Log(title = "字典类型", businessType = BusinessType.CLEAN)
//    @DeleteMapping("/refreshCache")
//    public AjaxResult refreshCache() {
//        return null;
//
//    }
//
//    /**
//     * 获取字典选择框列表
//     */
//    @GetMapping("/optionselect")
//    public AjaxResult optionselect() {
//        return null;
//
//    }
//}
