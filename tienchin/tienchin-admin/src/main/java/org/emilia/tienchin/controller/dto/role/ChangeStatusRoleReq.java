package org.emilia.tienchin.controller.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.emilia.tienchin.pojo.entity.SysRole;
import org.springframework.beans.BeanUtils;

@Data
public class ChangeStatusRoleReq {
    @NotNull
    private Long roleId;
    @NotBlank
    @Pattern(regexp = "^[01]$", message = "只能为0或1")
    private String status;
}
