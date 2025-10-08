package org.emilia.tienchin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.emilia.tienchin.controller.dto.role.ListSysRoleReq;
import org.emilia.tienchin.controller.system.SysRoleController;
import org.emilia.tienchin.pojo.entity.SysRole;
import org.emilia.tienchin.pojo.sys.SysConfig;
import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    List<SysRole> roles(Long userId);

    List<SysRole> selectRoles(ListSysRoleReq role);
}
