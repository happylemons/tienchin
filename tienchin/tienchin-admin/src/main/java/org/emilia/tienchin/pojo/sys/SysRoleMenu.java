package org.emilia.tienchin.pojo.sys;


/**
 * 角色和菜单关联 sys_role_menu
 */

import lombok.Data;

@Data
public class SysRoleMenu {
    private Long roleId;
    private Long menuId;
}
