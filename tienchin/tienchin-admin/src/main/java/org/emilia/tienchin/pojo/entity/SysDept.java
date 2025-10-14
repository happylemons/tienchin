package org.emilia.tienchin.pojo.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.emilia.tienchin.pojo.BaseEntity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门表 sys_dept
 *
 * @author tienchin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysDept extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long deptId;  //部门ID

    private Long parentId;  //父部门ID

    private String ancestors;  //祖级列表

    @NotBlank(message = "部门名称不能为空")
    @Size(min = 0, max = 30, message = "部门名称长度不能超过30个字符")
    private String deptName;  //部门名称

    private Integer orderNum;  //显示顺序

    private String leader;  //负责人

    @Size(min = 0, max = 11, message = "联系电话长度不能超过11个字符")
    private String phone;  //联系电话

    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    private String email;  //邮箱

    private String status;  //部门状态:0正常,1停用

    private String delFlag;  //删除标志（0代表存在 2代表删除）

    @TableField(exist = false)
    private String remark;  //备注

    //private String parentName;  //父部门名称

//    private List<SysDept> children = new ArrayList<SysDept>();  //子部门
}
