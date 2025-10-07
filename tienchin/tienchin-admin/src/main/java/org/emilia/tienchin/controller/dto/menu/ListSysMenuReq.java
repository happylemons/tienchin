package org.emilia.tienchin.controller.dto.menu;

import lombok.Data;

import jakarta.validation.constraints.Pattern;

@Data
public class ListSysMenuReq {
    private String menuName;
    @Pattern(regexp = "^[01]$", message = "只能为0或1")
    private String status;
}
