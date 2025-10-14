package org.emilia.tienchin.controller.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.emilia.tienchin.pojo.BaseEntity;
import org.emilia.tienchin.pojo.entity.SysRole;
import org.springframework.beans.BeanUtils;

@Data
public class EditSysRoleReq {
    @NotNull
    private Long roleId;
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
    @NotBlank(message = "权限字符不能为空")
    private String roleKey;
    @NotBlank(message = "角色顺序不能为空")
    private String roleSort;
    @Pattern(regexp = "^[01]$", message = "只能为0或1")
    private String status;
    private String dataScope;
    private boolean menuCheckStrictly;
    @Pattern(regexp = "^[02]$", message = "只能为0或2")
    private String delFlag;
    private Long[] menuIds;
    private String remark;
    public SysRole buildEntity() {
        SysRole role = new SysRole();
        BeanUtils.copyProperties(this, role);
        return role;
    }

}
