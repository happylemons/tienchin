package org.emilia.tienchin.controller.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.emilia.tienchin.pojo.entity.SysRole;
import org.emilia.tienchin.pojo.entity.SysUser;
import org.springframework.beans.BeanUtils;

@Data
public class EditSysUserReq {
    private Long deptId;

    @NotNull(message = "userId不能为空")
    private Long userId;
    @Email(message = "邮箱格式不正确")
    private String email;
    @Pattern(regexp = "^1(?:3[0-9]|4[579]|5[0-35-9]|6[6]|7[0135-8]|8[0-9]|9[13589])\\d{8}$", message = "手机格式不正确")
    private String phonenumber;
    @Pattern(regexp = "^[01]$", message = "只能为0或1")
    private String status;
    @Pattern(regexp = "^[01]$", message = "只能为0或1")
    private String sex;

    private String remark;

    private Long[] roleIds;
    private Long[] postIds;

    public SysUser buildEntity() {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(this, user);
        return user;
    }

}
