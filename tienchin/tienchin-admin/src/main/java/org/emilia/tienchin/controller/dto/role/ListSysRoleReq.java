package org.emilia.tienchin.controller.dto.role;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ListSysRoleReq {
    private String roleName;
    private String roleKey;
    @Pattern(regexp = "^[01]$", message = "只能为0或1")
    private String status;
}
