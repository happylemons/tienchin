package org.emilia.tienchin.controller.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class DataScopeSysRoleReq {
    @NotNull
    private Long roleId;
    @NotBlank(message = "dataScope不能为空")
    @Pattern(regexp = "^[1-5]$", message = "dataScope必须是1到5之间的数字")
    private String dataScope;
    private boolean deptCheckStrictly;
    private Long[] deptIds;
}
