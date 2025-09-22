package org.emilia.tienchin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.emilia.tienchin.pojo.entity.SysDept;

public interface SysDeptMapper extends BaseMapper<SysDept> {
    SysDept selectByDeptId(Long deptId);
}
