package org.emilia.tienchin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.emilia.tienchin.controller.dto.dept.AddSysDeptReq;
import org.emilia.tienchin.controller.dto.dept.EditSysDeptReq;
import org.emilia.tienchin.controller.dto.dept.ListSysDeptReq;
import org.emilia.tienchin.mapper.*;
import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.pojo.business.TreeSelect;
import org.emilia.tienchin.pojo.entity.SysDept;
import org.emilia.tienchin.pojo.entity.SysDictData;
import org.emilia.tienchin.pojo.entity.SysRole;
import org.emilia.tienchin.pojo.entity.SysUser;
import org.emilia.tienchin.pojo.sys.SysRoleDept;
import org.emilia.tienchin.service.SysDeptService;
import org.emilia.tienchin.service.SysDictDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.emilia.tienchin.exception.ParamValidException.buildParamException;

@Service
@RequiredArgsConstructor
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    private final SysDeptMapper sysDeptMapper;
    private final SysUserMapper sysUserMapper;
    private final SysRoleDeptMapper roleDeptMapper;
    private final SysRoleMapper sysRoleMapper;


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
    public List<SysDept> excludeChild(Long deptId) {
        List<SysDept> allDepts = sysDeptMapper.selectList(null);
        List<SysDept> excludeList = allDepts.stream()
                .filter(d -> !d.getDeptId().equals(deptId))
                .filter(d -> !d.getAncestors().contains(deptId.toString()))
                .collect(Collectors.toList());
        return excludeList;
    }

    @Override
    public SysDept selectByDeptId(Long deptId) {
        SysDept sysDept = sysDeptMapper.selectByDeptId(deptId);
        if (sysDept == null) {
            throw buildParamException("deptId", "部门不存在");
        }
        return sysDept;
    }

    @Override
    public List<Long> checkedKeys(Long roleId) {
        SysRole role = sysRoleMapper.selectOne(new QueryWrapper<SysRole>().eq("role_id", roleId));
        if (role == null) {
            throw buildParamException("roleId", "角色不存在");
        }
        List<SysRoleDept> list = roleDeptMapper.selectList(new QueryWrapper<SysRoleDept>().eq("role_id", roleId));

        return list.stream()
                .map(SysRoleDept::getDeptId)
                .filter(x -> x == 100L || x == 101L || x == 102L)  // example filter condition
                .collect(Collectors.toList());
    }

    @Override
    public List<TreeSelect> treeselect() {
        List<SysDept> allDepts = sysDeptMapper.selectList(null);
        List<TreeSelect> treeDepts = buildTreeDepts(allDepts, 0L);
        return treeDepts;
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

    @Override
    public AjaxResult add(AddSysDeptReq dept) {
        //1. patentId存在
        Long parentId = dept.getParentId();
        SysDept existingDept = sysDeptMapper.selectOne(new QueryWrapper<SysDept>().eq("dept_id", parentId));
        if (existingDept == null) {
            throw buildParamException("parentId", "父部门不存在");
        }
        //2. deptName唯一
        String deptName = dept.getDeptName();
        String ancestors = existingDept.getAncestors() + "," + parentId.toString();
        List<SysDept> sysDepts = sysDeptMapper.selectList(null);
        List<SysDept> deptExist = sysDepts.stream()
                .filter(x -> !x.getAncestors().equals(ancestors))
                .filter(x -> x.getDeptName().equals(deptName))
                .collect(Collectors.toList());
        if (!deptExist.isEmpty()) {
            throw buildParamException("deptName", "部门名称 " + deptName + " 已存在");
        }

        SysDept entity = dept.BuildEntity();
        entity.setAncestors(ancestors);
        entity.setCreateTime(new Date(System.currentTimeMillis()));
        sysDeptMapper.insert(entity);
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult edit(EditSysDeptReq dept) {
        Long deptId = dept.getDeptId();
        SysDept existingDept = sysDeptMapper.selectOne(new QueryWrapper<SysDept>().eq("dept_id", deptId));
        if (existingDept == null) {
            throw buildParamException("deptId", "部门不存在");
        }
        Long parentId = dept.getParentId();
        SysDept parentDept = sysDeptMapper.selectOne(new QueryWrapper<SysDept>().eq("dept_id", parentId));
        if (parentDept == null) {
            throw buildParamException("parentId", "父部门不存在");
        }
        String ancestors = parentDept.getAncestors() + "," + parentId.toString();
        SysDept entity = dept.BuildEntity();
        entity.setAncestors(ancestors);
        entity.setUpdateTime(new Date(System.currentTimeMillis()));
        sysDeptMapper.update(entity, new UpdateWrapper<SysDept>().eq("dept_id", deptId));
        return AjaxResult.success();
    }

    @Override
    public AjaxResult remove(Long deptId) {
        SysDept existingDept = sysDeptMapper.selectOne(new QueryWrapper<SysDept>().eq("dept_id", deptId));
        if (existingDept == null) {
            throw buildParamException("deptId", "部门不存在");
        }
        List<SysDept> parentDept = sysDeptMapper.selectList(new QueryWrapper<SysDept>().eq("parent_id", deptId));
        if (parentDept.size()>0) {
            throw buildParamException("deptId", "存在下级部门，无法删除");
        }
        sysDeptMapper.delete(new QueryWrapper<SysDept>().eq("dept_id", deptId));
        return AjaxResult.success();
    }
}

