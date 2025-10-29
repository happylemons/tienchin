package org.emilia.tienchin.controller.dto.user;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.emilia.tienchin.pojo.entity.SysUser;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

@Data
public class AddSysUserReq {

    @Email(message = "邮箱格式不正确")
    private String email;
    @NotBlank(message = "角色名称不能为空")
    private String userName;
    @NotBlank(message = "用户昵称不能为空")
    private String nickName;
    @Pattern(regexp = "^1(?:3[0-9]|4[579]|5[0-35-9]|6[6]|7[0135-8]|8[0-9]|9[13589])\\d{8}$", message = "手机格式不正确")
    private String phonenumber;
    @NotBlank(message = "用户密码不能为空")
    private String password;
    @Pattern(regexp = "^[01]$", message = "只能为0或1")
    private String status;
    @Pattern(regexp = "^[01]$", message = "只能为0或1")
    private String sex;

    private String remark;

    private Long deptId;
    private Long[] roleIds;
    private Long[] postIds;
    public SysUser buildEntity() {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}
