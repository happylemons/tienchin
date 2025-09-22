package org.emilia.tienchin.controller.system;

import javax.servlet.http.HttpServletResponse;

import lombok.extern.java.Log;
import org.emilia.tienchin.controller.common.BaseController;
import org.emilia.tienchin.enums.BusinessType;
import org.emilia.tienchin.pojo.business.TableDataInfo;
import org.emilia.tienchin.pojo.entity.SysDictData;
import org.emilia.tienchin.service.SysDictDataService;
import org.emilia.tienchin.pojo.AjaxResult;

import org.emilia.tienchin.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据字典信息
 *
 * @author tienchin
 */
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController {
    @Autowired(required = false)
    private SysDictDataService dictDataService;

    @Autowired(required = false)
    private SysDictTypeService dictTypeService;

    @PreAuthorize("hasPermission('system:dict:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysDictData dictData) {
        return null;

    }

//    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
    @PreAuthorize("hasPermission('system:dict:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDictData dictData) {

    }

    /**
     * 查询字典数据详细
     */
    @PreAuthorize("hasPermission('system:dict:query')")
    @GetMapping(value = "/{dictCode}")
    public AjaxResult getInfo(@PathVariable Long dictCode) {
        return null;

    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType) {
        return null;

    }

    /**
     * 新增字典类型
     */
    @PreAuthorize("hasPermission('system:dict:add')")
//    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysDictData dict) {
        return null;

    }

    /**
     * 修改保存字典类型
     */
    @PreAuthorize("hasPermission('system:dict:edit')")
//    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysDictData dict) {
        return null;

    }

    /**
     * 删除字典类型
     */
    @PreAuthorize("hasPermission('system:dict:remove')")
//    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictCodes}")
    public AjaxResult remove(@PathVariable Long[] dictCodes) {
        return null;

    }
}
