package org.emilia.tienchin.pojo.sys;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户和角色关联 sys_user_role
 */

@Data
public class SysUserRole {
    private Long userId;
    private Long roleId;
}
