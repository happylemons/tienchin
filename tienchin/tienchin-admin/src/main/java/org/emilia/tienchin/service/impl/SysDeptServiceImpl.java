package org.emilia.tienchin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.emilia.tienchin.controller.dto.dept.ListSysDeptReq;
import org.emilia.tienchin.mapper.SysDeptMapper;
import org.emilia.tienchin.mapper.SysDictDataMapper;
import org.emilia.tienchin.mapper.SysUserMapper;
import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.pojo.business.TreeSelect;
import org.emilia.tienchin.pojo.entity.SysDept;
import org.emilia.tienchin.pojo.entity.SysDictData;
import org.emilia.tienchin.pojo.entity.SysUser;
import org.emilia.tienchin.service.SysDeptService;
import org.emilia.tienchin.service.SysDictDataService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    private final SysDeptMapper sysDeptMapper;
    private final SysUserMapper sysUserMapper;

    @Override
    public List<SysDept> selectDepts(ListSysDeptReq dept) {
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();
        if (dept != null) {
            if (dept.getDeptName() != null) {
                queryWrapper.like("dept_name", dept.getDeptName());
            }
            if (dept.getStatus() != null) {
                queryWrapper.eq("status", dept.getStatus());
            }
        }
        return sysDeptMapper.selectList(queryWrapper);
    }

    @Override
    public AjaxResult treeselect(Long userId) {
        SysUser sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("user_id", userId));
        if (sysUser == null) {
            return AjaxResult.error("用户不存在");
        }
        if (userId == 1L) {
            List<SysDept> allDepts = sysDeptMapper.selectList(null);
            List<TreeSelect> treeDepts = buildTreeDepts(allDepts, 0L);
            return AjaxResult.success(treeDepts);
        }
        return null;
    }


    private List<TreeSelect> buildTreeDepts(List<SysDept> allDepts, Long parentId) {
        return allDepts.stream()
                .filter(dept -> dept.getParentId().equals(parentId))
                .map(dept -> {
                    TreeSelect treeSelect = new TreeSelect(dept);
                    List<TreeSelect> children = buildTreeDepts(allDepts, dept.getDeptId());
                    treeSelect.setChildren(children);
                    return treeSelect;
                })
                .collect(Collectors.toList());
    }
}

