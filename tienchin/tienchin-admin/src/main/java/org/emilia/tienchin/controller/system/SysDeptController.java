package org.emilia.tienchin.controller.system;


import org.emilia.tienchin.controller.common.BaseController;
import org.emilia.tienchin.controller.dto.dept.ListSysDeptReq;
import org.emilia.tienchin.pojo.AjaxResult;
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
        return null;

    }

    /**
     * 根据部门编号获取详细信息
     */
//    @PreAuthorize("hasPermission('system:dept:query')")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable Long deptId) {
        return null;

    }

    /**
     * 获取部门下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect() {
        Long userId = getUserId();
        if (userId == null) {
            return AjaxResult.error("获取用户id错误");
        }
        return deptService.treeselect(userId);

    }

    /**
     * 加载对应角色部门列表树
     */
    @GetMapping(value = "/roleDeptTreeselect/{roleId}")
    public AjaxResult roleDeptTreeselect(@PathVariable("roleId") Long roleId) {
        return null;

    }

    /**
     * 新增部门
     */
//    @PreAuthorize("hasPermission('system:dept:add')")
//    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysDept dept) {
        return null;

    }

    /**
     * 修改部门
     */
//    @PreAuthorize("hasPermission('system:dept:edit')")
//    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysDept dept) {
        return null;

    }

    /**
     * 删除部门
     */
//    @PreAuthorize("hasPermission('system:dept:remove')")
//    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deptId}")
    public AjaxResult remove(@PathVariable Long deptId) {
        return null;

    }
}
