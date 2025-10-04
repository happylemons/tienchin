package org.emilia.tienchin.pojo.sys;


/**
 * 角色和菜单关联 sys_role_menu
 *
 * @author tienchin
 */

import lombok.Data;

@Data
public class SysRoleMenu {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;
}
