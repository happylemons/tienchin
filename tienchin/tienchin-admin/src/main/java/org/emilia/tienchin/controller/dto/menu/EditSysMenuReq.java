package org.emilia.tienchin.controller.dto.menu;

import lombok.Data;
import org.emilia.tienchin.enums.MenuType;
import org.emilia.tienchin.pojo.entity.SysMenu;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
public class EditSysMenuReq {

    @NotNull(message = "菜单Id不能为空")
    private Long menuId;

    @NotBlank(message = "菜单名称不能为空")
    @Size(min = 0, max = 50, message = "菜单名称长度不能超过50个字符")
    private String menuName;

    @NotNull(message = "父ID不能为空")
    private Integer orderNum;

    @Size(min = 0, max = 200, message = "路由地址不能超过200个字符")
    private String path;

    @Size(min = 0, max = 200, message = "组件路径不能超过255个字符")
    private String component;

    @Pattern(regexp = "^[01]$", message = "只能为0或1")
    private String isFrame;

    @Pattern(regexp = "^[01]$", message = "只能为0或1")
    private String isCache;

    @NotNull(message = "菜单类型不能为空")
    private MenuType menuType;

    @Pattern(regexp = "^[01]$", message = "只能为0或1")
    private String visible;

    @Pattern(regexp = "^[01]$", message = "只能为0或1")
    private String status;

    @Size(min = 0, max = 100, message = "权限标识长度不能超过100个字符")
    private String perms;


    private String icon;
    private Long parentId;
    private String query;

    public SysMenu buildEntity(){
        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(this, sysMenu);
        return sysMenu;
    }

}
