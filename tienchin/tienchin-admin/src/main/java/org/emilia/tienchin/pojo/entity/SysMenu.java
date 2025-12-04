package org.emilia.tienchin.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.emilia.tienchin.pojo.BaseEntity;



import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限表 sys_menu
 *
 * @author tienchin
 */
@Data
public class SysMenu extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId
    private Long menuId;

    /**
     * 菜单名称
     */

    @NotBlank(message = "菜单名称不能为空")
    @Size(min = 0, max = 50, message = "菜单名称长度不能超过50个字符")
    private String menuName;

    /**
     * 父菜单名称
     */
    @TableField(exist = false)
    private String parentName;

    /**
     * 父菜单ID
     */
    @NotNull(message = "父ID不能为空")
    private Long parentId;

    /**
     * 显示顺序
     */
//    @NotNull(message = "显示顺序不能为空")
    private Integer orderNum;

    /**
     * 路由地址
     */
    @Size(min = 0, max = 200, message = "路由地址不能超过200个字符")
    private String path;

    /**
     * 组件路径
     */
    @Size(min = 0, max = 200, message = "组件路径不能超过255个字符")
    private String component;

    /**
     * 路由参数
     */
    private String query;

    /**
     * 是否为外链（0是 1否）
     */
    @Pattern(regexp = "^[01]$", message = "只能为0或1")
    private String isFrame;

    /**
     * 是否缓存（0缓存 1不缓存）
     */
    @Pattern(regexp = "^[01]$", message = "只能为0或1")
    private String isCache;

    /**
     * 类型（M目录 C菜单 F按钮）
     */
    @Pattern(regexp = "^[MCF]$",message = "只能为M/C/F")
    @NotBlank(message = "菜单类型不能为空")
    private String menuType;

    /**
     * 显示状态（0显示 1隐藏）
     */
    @Pattern(regexp = "^[01]$", message = "只能为0或1")
    private String visible;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    @Pattern(regexp = "^[01]$", message = "只能为0或1")
    private String status;

    /**
     * 权限字符串
     */
    @Size(min = 0, max = 100, message = "权限标识长度不能超过100个字符")
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<SysMenu> children = new ArrayList<>();

}
