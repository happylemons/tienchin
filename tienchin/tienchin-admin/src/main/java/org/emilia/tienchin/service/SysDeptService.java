package org.emilia.tienchin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.emilia.tienchin.controller.dto.dept.ListSysDeptReq;
import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.pojo.entity.SysDept;
import org.emilia.tienchin.pojo.entity.SysDictData;

import java.util.List;

public interface SysDeptService extends IService<SysDept> {

    AjaxResult treeselect(Long userId);

    List<SysDept> selectDepts(ListSysDeptReq dept);
}
