package org.emilia.tienchin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.emilia.tienchin.controller.dto.dept.AddSysDeptReq;
import org.emilia.tienchin.controller.dto.dept.EditSysDeptReq;
import org.emilia.tienchin.controller.dto.dept.ListSysDeptReq;
import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.pojo.business.TreeSelect;
import org.emilia.tienchin.pojo.entity.SysDept;
import org.emilia.tienchin.pojo.entity.SysDictData;

import java.util.List;

public interface SysDeptService extends IService<SysDept> {

    List<TreeSelect> treeselect();

    List<SysDept> selectDepts(ListSysDeptReq dept);

    List<SysDept> excludeChild(Long deptId);

    SysDept selectByDeptId(Long deptId);

    List<Long> checkedKeys(Long roleId);

    AjaxResult add(AddSysDeptReq dept);

    AjaxResult edit(EditSysDeptReq dept);

    AjaxResult remove(Long deptId);
}
