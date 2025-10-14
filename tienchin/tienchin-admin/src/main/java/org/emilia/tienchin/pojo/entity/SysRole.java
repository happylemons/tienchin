package org.emilia.tienchin.pojo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.emilia.tienchin.pojo.BaseEntity;

/**
 * 角色表 sys_role
 *
 * @author tienchin
 */
@Data
public class SysRole extends BaseEntity {
    private static final long serialVersionUID = 1L;
    //    @Excel(name = "角色序号", cellType = ColumnType.NUMERIC)
    @TableId(type = IdType.AUTO)
    private Long roleId;                    //角色ID

    //    @Excel(name = "角色名称")
    private String roleName;                //角色名称

    //    @Excel(name = "角色权限")
    private String roleKey;                  //角色权限

    //    @Excel(name = "角色排序")
    private String roleSort;                 //角色排序

//    @Excel(name = "数据范围", readConverterExp = "1=所有数据权限,2=自定义数据权限,3=本部门数据权限,4=本部门及以下数据权限,5=仅本人数据权限")
    private String dataScope;  //数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限）

    private boolean menuCheckStrictly;  //菜单树选择项是否关联显示（ 0：父子不互相关联显示 1：父子互相关联显示）

    private boolean deptCheckStrictly;  //部门树选择项是否关联显示（0：父子不互相关联显示 1：父子互相关联显示 ）

    //    @Excel(name = "角色状态", readConverterExp = "0=正常,1=停用")
    private String status;  //角色状态（0正常 1停用）

    private String delFlag; //删除标志（0代表存在 2代表删除）

    @TableField(exist = false)
    private boolean flag = false;  //用户是否存在此角色标识 默认不存在

    @TableField(exist = false)
    private Long[] menuIds;  //菜单组

    @TableField(exist = false)
    private Long[] deptIds;  //部门组（数据权限）
}
