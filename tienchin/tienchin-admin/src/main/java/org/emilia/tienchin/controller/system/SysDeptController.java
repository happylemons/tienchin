package org.emilia.tienchin.controller.system;


import org.emilia.tienchin.controller.common.BaseController;
import org.emilia.tienchin.controller.dto.dept.AddSysDeptReq;
import org.emilia.tienchin.controller.dto.dept.EditSysDeptReq;
import org.emilia.tienchin.controller.dto.dept.ListSysDeptReq;
import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.pojo.business.TreeSelect;
import org.emilia.tienchin.pojo.entity.SysDept;
import org.emilia.tienchin.service.SysDeptService;
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

import java.util.List;

//部门信息
@RestController
@RequestMapping("/system/dept")
public class SysDeptController extends BaseController {
    @Autowired
    private SysDeptService deptService;

    /**
     * 获取部门列表
     */
//    @PreAuthorize("hasPermission('system:dept:list')")
    @GetMapping("/list")
    public AjaxResult list(ListSysDeptReq dept) {
        List<SysDept> result = deptService.selectDepts(dept);
        return AjaxResult.success(result);

    }

    /**
     * 查询部门列表（排除节点）
     */
//    @PreAuthorize("hasPermission('system:dept:list')")
    @GetMapping("/list/exclude/{deptId}")
    public AjaxResult excludeChild(@PathVariable(value = "deptId", required = false) Long deptId) {
        List<SysDept> result = deptService.excludeChild(deptId);
        return AjaxResult.success(result);

    }

    /**
     * 根据部门编号获取详细信息
     */
//    @PreAuthorize("hasPermission('system:dept:query')")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable(value = "deptId") Long deptId) {
        SysDept dept = deptService.selectByDeptId(deptId);
        return AjaxResult.success(dept);
    }

    /**
     * 获取部门下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect() {
        List<TreeSelect> result = deptService.treeselect();
        return AjaxResult.success(result);
    }

    /**
     * 加载对应角色部门列表树
     */
    @GetMapping(value = "/roleDeptTreeselect/{roleId}")
    public AjaxResult roleDeptTreeselect(@PathVariable("roleId") Long roleId) {
        List<TreeSelect> depts = deptService.treeselect();
        AjaxResult result = AjaxResult.success();
        result.put("depts", depts);
        result.put("checkedKeys", deptService.checkedKeys(roleId));
        return result;
    }

    /**
     * 新增部门
     */
//    @PreAuthorize("hasPermission('system:dept:add')")
//    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody AddSysDeptReq dept) {
        return deptService.add(dept);
    }

    /**
     * 修改部门
     */
//    @PreAuthorize("hasPermission('system:dept:edit')")
//    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody EditSysDeptReq dept) {
        return deptService.edit(dept);

    }

    /**
     * 删除部门
     */
//    @PreAuthorize("hasPermission('system:dept:remove')")
//    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deptId}")
    public AjaxResult remove(@PathVariable("deptId") Long deptId) {
        return deptService.remove(deptId);
    }
}
