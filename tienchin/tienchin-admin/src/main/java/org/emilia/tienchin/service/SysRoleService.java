package org.emilia.tienchin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.emilia.tienchin.config.PageImpl;
import org.emilia.tienchin.controller.dto.role.*;
import org.emilia.tienchin.controller.system.SysRoleController;
import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.pojo.entity.SysRole;
import org.emilia.tienchin.pojo.entity.SysUser;
import org.emilia.tienchin.pojo.sys.SysConfig;
import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    List<SysRole> roles(Long userId);

    List<SysRole> selectRoles(ListSysRoleReq role, Page<SysRole> page);

    SysRole selectRoleById(Long roleId);

    AjaxResult add(AddSysRoleReq role);

    AjaxResult edit(EditSysRoleReq role);

    AjaxResult dataScope(DataScopeSysRoleReq role);

    AjaxResult changeStatus(ChangeStatusRoleReq role);

    AjaxResult remove(Long[] roleIds);

    AjaxResult optionselect();

    List<SysUser> allocatedList(Long roleId, Page<SysUser> page);

    List<SysUser> unallocatedList(Long roleId, Page<SysUser> page);

    AjaxResult cancelAuthUser(Long roleId, Long userId);

    AjaxResult cancelAuthUserAll(Long roleId, Long[] userIds);

    AjaxResult selectAuthUserAll(Long roleId, Long[] userIds);

}
