package org.emilia.tienchin.controller.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ChangeStatusUserReq {
    @NotNull
    private Long userId;
    @NotBlank
    @Pattern(regexp = "^[01]$", message = "只能为0或1")
    private String status;
}
