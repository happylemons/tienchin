package org.emilia.tienchin.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.emilia.tienchin.pojo.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限表 sys_menu
 *
 * @author tienchin
 */
@Data
public class SysMenu2 extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long menuId;                      //菜单ID

}
