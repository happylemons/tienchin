package org.emilia.tienchin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.SelectPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.emilia.tienchin.controller.dto.role.*;
import org.emilia.tienchin.mapper.*;
import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.pojo.business.TableDataInfo;
import org.emilia.tienchin.pojo.entity.SysDept;
import org.emilia.tienchin.pojo.entity.SysMenu;
import org.emilia.tienchin.pojo.entity.SysRole;
import org.emilia.tienchin.pojo.entity.SysUser;
import org.emilia.tienchin.pojo.sys.SysRoleDept;
import org.emilia.tienchin.pojo.sys.SysRoleMenu;
import org.emilia.tienchin.pojo.sys.SysUserRole;
import org.emilia.tienchin.service.SysConfigService;
import org.emilia.tienchin.service.SysRoleService;
import org.emilia.tienchin.service.SysUserService;
import org.emilia.tienchin.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.emilia.tienchin.exception.ParamValidException.buildParamException;

@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysUserMapper sysUserMapper;
    private final SysRoleMapper sysRoleMapper;
    private final SysMenuMapper sysMenuMapper;
    private final SysDeptMapper sysDeptMapper;
    private final SysRoleDeptMapper roleDeptMapper;
    private final SysUserRoleMapper userRoleMapper;

    @Override
    public List<SysRole> roles(Long userId) {
        return sysRoleMapper.selectByUserId(userId);
    }

    @Override
    public List<SysRole> selectRoles(ListSysRoleReq role, Page<SysRole> page) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        if (role != null) {
            if (role.getRoleName() != null) {
                String roleName = role.getRoleName().replaceAll("^\\s+", "");
                queryWrapper.like("role_name", roleName);
            }
            if (role.getRoleKey() != null) {
                queryWrapper.like("role_key", role.getRoleKey());
            }
            if (role.getStatus() != null) {
                queryWrapper.eq("status", role.getStatus());
            }
        }
        return sysRoleMapper.selectPage(page, queryWrapper).getRecords();
    }

    @Override
    public SysRole selectRoleById(Long roleId) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<SysRole>().eq("role_id", roleId);
        SysRole role = sysRoleMapper.selectOne(queryWrapper);
        return role;
    }

    @Override
    public AjaxResult add(AddSysRoleReq role) {
        String roleName = role.getRoleName();
        SysRole old = sysRoleMapper.selectOne(new QueryWrapper<SysRole>().eq("role_name", roleName));
        if (old != null) {
            throw buildParamException("roleName", "当前已存在该名称: " + roleName);
        }
        String roleKey = role.getRoleKey();
        SysRole old1 = sysRoleMapper.selectOne(new QueryWrapper<SysRole>().eq("role_key", roleKey));
        if (old1 != null) {
            throw buildParamException("roleKey", "当前已存在该权限: " + roleKey);
        }
        if (role.getMenuIds() != null) {
            validateMenuIds(role.getMenuIds());
        }
        SysRole sysRole = role.buildEntity();
        sysRole.setRoleId(null);
        sysRole.setCreateTime(new Date(System.currentTimeMillis()));
        sysRoleMapper.insert(sysRole);
        return AjaxResult.success();
    }

    private void validateMenuIds(Long[] menuIds) {
        for (Long menuId : menuIds) {
            SysMenu sysMenu = sysMenuMapper.selectOne(new QueryWrapper<SysMenu>().eq("menu_id", menuId));
            if (sysMenu == null) {
                throw buildParamException("menuIds", "存在不存在的menuId: " + menuId);
            }
        }
    }

    @Override
    @Transactional
    public AjaxResult edit(EditSysRoleReq role) {
        Long roleId = role.getRoleId();
        validateRoleIdExists(roleId);
        //直接删除原本的
        sysRoleMapper.deleteById(roleId);
        SysRole oldName = sysRoleMapper.selectOne(new QueryWrapper<SysRole>().eq("role_name", role.getRoleName()));
        if (oldName != null) {
            throw buildParamException("roleName", "当前已存在该名称: " + role.getRoleName());
        }
        SysRole oldKey = sysRoleMapper.selectOne(new QueryWrapper<SysRole>().eq("role_key", role.getRoleKey()));
        if (oldKey != null) {
            throw buildParamException("roleKey", "当前已存在该权限 : " + role.getRoleKey());
        }

        if (role.getMenuIds() != null) {
            validateMenuIds(role.getMenuIds());
        }
        SysRole sysRole = role.buildEntity();
        sysRole.setUpdateTime(new Date(System.currentTimeMillis()));
        sysRoleMapper.insert(sysRole);
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult dataScope(DataScopeSysRoleReq role) {
        Long roleId = role.getRoleId();
        SysRole sysRole = validateRoleIdExists(roleId);
        Long[] deptIds = role.getDeptIds();
        if ("2".equals(role.getDataScope()) && deptIds != null) {
            validateDeptIds(deptIds);
            sysRole.setDeptCheckStrictly(role.isDeptCheckStrictly());
            roleDeptMapper.delete(new QueryWrapper<SysRoleDept>().eq("role_id", roleId));
            for (Long deptId : deptIds) {
                roleDeptMapper.insert(new SysRoleDept(roleId, deptId));
            }
        }
        if (!"2".equals(role.getDataScope()) && deptIds != null) {
            throw buildParamException("deptIds", "当前roleId" + roleId + ", 不能单独设置deptIds: " + Arrays.toString(deptIds));
        }
        sysRole.setDataScope(role.getDataScope());
        sysRole.setUpdateTime(new Date(System.currentTimeMillis()));
        sysRoleMapper.updateById(sysRole);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult changeStatus(ChangeStatusRoleReq role) {
        Long roleId = role.getRoleId();
        SysRole sysRole = validateRoleIdExists(roleId);
        sysRole.setStatus(role.getStatus());
        sysRole.setUpdateTime(new Date(System.currentTimeMillis()));
        sysRoleMapper.updateById(sysRole);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult remove(Long[] roleIds) {
        for (Long roleId : roleIds) {
            //1. id都存在, 且没有分配给user->
            validateRoleIdExists(roleId);
            List<SysUserRole> assignToUser = userRoleMapper.selectList(new QueryWrapper<SysUserRole>().eq("role_id", roleId));
            if (!assignToUser.isEmpty()) {
                throw buildParamException("roleId", "roleId:" + roleId + " 已经有分配给user");
            } else {
                sysRoleMapper.deleteById(roleId);
            }
        }
        return AjaxResult.success();
    }

    @Override
    public AjaxResult optionselect() {
        List<SysRole> sysRoles = sysRoleMapper.selectList(new QueryWrapper<SysRole>().eq("del_flag", 0));
        return AjaxResult.success(sysRoles);
    }


    @Override
    public List<SysUser> allocatedList(Long roleId, Page<SysUser> page) {
        Page<SysUserRole> userRolePage = new Page<>(page.getCurrent(), page.getSize());
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<SysUserRole>().eq("role_id", roleId);
        Page<SysUserRole> list = userRoleMapper.selectPage(userRolePage, queryWrapper);
        List<Long> userIds = list.getRecords().stream()
                .map(SysUserRole::getUserId)
                .collect(Collectors.toList());
        List<SysUser> result = userIds.stream().map(
                        userId -> sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("user_id", userId)))
                .collect(Collectors.toList());
        page.setRecords(result);
        page.setTotal(result.size());
        return page.getRecords();
    }

    @Override
    public List<SysUser> unallocatedList(Long roleId, Page<SysUser> page) {
        List<SysUserRole> list = userRoleMapper.selectList(new QueryWrapper<SysUserRole>().eq("role_id", roleId));
        List<Long> allocated = list.stream().map(SysUserRole::getUserId).collect(Collectors.toList());
        List<SysUser> users = sysUserMapper.selectList(null);
        List<SysUser> result = users.stream().filter(user -> !allocated.contains(user.getUserId()))
                .collect(Collectors.toList());
        toPage(result, page);
        return page.getRecords();
    }

    @Override
    public AjaxResult cancelAuthUser(Long roleId, Long userId) {
        roleExists(roleId);
        userExists(userId);
        roleUserExists(roleId, userId);
        userRoleMapper.delete(new QueryWrapper<SysUserRole>().eq("role_id", roleId).eq("user_id", userId));
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult cancelAuthUserAll(Long roleId, Long[] userIds) {
        roleExists(roleId);
        for (Long userId : userIds) {
            userExists(userId);
            roleUserExists(roleId, userId);
        }
        for (Long userId : userIds) {
            QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<SysUserRole>().eq("role_id", roleId).eq("user_id", userId);
            userRoleMapper.delete(queryWrapper);
        }
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult selectAuthUserAll(Long roleId, Long[] userIds) {

        //1. 角色存在
        roleExists(roleId);
        //2. 用户存在
        for (Long userId : userIds) {
            userExists(userId);
            //3. 对应关系不存在
            roleUserNotExists(roleId, userId);
            userRoleMapper.insert(new SysUserRole(roleId, userId));
        }
        return AjaxResult.success();
    }


    private void roleExists(Long roleId) {
        SysRole sysRole = sysRoleMapper.selectOne(new QueryWrapper<SysRole>().eq("role_id", roleId));
        if (sysRole == null) {
            throw buildParamException("roleId", "当前角色" + roleId + "不存在");
        }
    }

    private void userExists(Long userId) {
        SysUser user = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("user_id", userId));
        if (user == null) {
            throw buildParamException("userId", "用户" + userId + "不存在");
        }
    }

    @Transactional
    private void roleUserExists(Long roleId, Long userId) {
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<SysUserRole>().eq("user_id", userId).eq("role_id", roleId);
        SysUserRole userRole = userRoleMapper.selectOne(queryWrapper);
        if (userRole == null) {
            throw buildParamException("roleId/userId", "该角色roleId: " + roleId + "未被分配至该用户userId:" + userId);
        }
    }

    private void roleUserNotExists(Long roleId, Long userId) {
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<SysUserRole>().eq("user_id", userId).eq("role_id", roleId);
        SysUserRole userRole = userRoleMapper.selectOne(queryWrapper);
        if (userRole != null) {
            throw buildParamException("roleId/userId", "该角色roleId: " + roleId + "已经分配至该用户userId:" + userId);
        }
    }

    private <T> void toPage(List<T> list, Page<T> page) {
        long pageNum = page.getCurrent();
        long pageSize = page.getSize();
        if (list != null) {
            List<T> result = list.stream()
                    .skip((pageNum - 1) * pageSize)
                    .limit(pageSize)
                    .collect(Collectors.toList());
            page.setRecords(result);
            page.setTotal(list.size());
        }
    }

    private SysRole validateRoleIdExists(Long roleId) {
        SysRole sysRole = sysRoleMapper.selectOne(new QueryWrapper<SysRole>().eq("role_id", roleId));
        if (sysRole == null) {
            throw buildParamException("roleId", "当前roleId不存在 " + roleId);
        }
        return sysRole;
    }

    private void validateDeptIds(Long[] deptIds) {
        for (Long deptId : deptIds) {
            SysDept sysDept = sysDeptMapper.selectOne(new QueryWrapper<SysDept>().eq("dept_id", deptId));
            if (sysDept == null) {
                throw buildParamException("deptIds", "存在不存在的deptId: " + deptId);
            }
        }
    }
}
