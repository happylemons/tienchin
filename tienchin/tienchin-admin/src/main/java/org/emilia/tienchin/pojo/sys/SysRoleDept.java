package org.emilia.tienchin.pojo.sys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 角色和部门关联 sys_role_dept
 *
 * @author tienchin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleDept {
    private Long roleId;   //角色ID
    private Long deptId;  //部门ID
}
