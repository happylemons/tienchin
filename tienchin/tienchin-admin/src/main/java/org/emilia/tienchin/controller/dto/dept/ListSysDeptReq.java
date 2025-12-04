package org.emilia.tienchin.controller.dto.dept;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ListSysDeptReq {
    private String deptName;
    @Pattern(regexp = "^[01]$", message = "只能为0或1")
    private String status;
}
