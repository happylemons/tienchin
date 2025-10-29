package org.emilia.tienchin.pojo.sys;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户和岗位关联 sys_user_post
 *
 * @author tienchin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserPost {
    private Long userId;  //用户ID
    private Long postId;  //岗位ID
}
