package org.emilia.tienchin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.emilia.tienchin.pojo.entity.SysRole;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> selectByUserId(Long userId);
}
