package org.emilia.tienchin.pojo.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.emilia.tienchin.pojo.BaseEntity;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class SysUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //    @Excel(name = "用户序号", cellType = ColumnType.NUMERIC, prompt = "用户编号")
    @TableId
    private Long userId;  //用户ID

    //        @Excel(name = "部门编号", type = Type.IMPORT)
    private Long deptId;  //部门ID

    //    @Excel(name = "登录名称")
    private String userName;  //用户账号

    //    @Excel(name = "用户名称")
    private String nickName;  //用户昵称

    //    @Excel(name = "用户邮箱")
    private String email;  //用户邮箱

    //    @Excel(name = "手机号码")
    private String phonenumber;  //手机号码

    //    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;  //用户性别

    private String avatar;  //用户头像

    private String password;  //密码

    @TableField(exist = false)
    private String salt;  //盐加密

    //    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;  //帐号状态（0正常 1停用）

    private String delFlag;  //删除标志（0代表存在 2代表删除）

    //    @Excel(name = "最后登录IP", type = Type.EXPORT)
    private String loginIp;  //最后登录IP

    //    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    private Date loginDate;  //最后登录时间

    //    @Excels({
//            @Excel(name = "部门名称", targetAttr = "deptName", type = Type.EXPORT),
//            @Excel(name = "部门负责人", targetAttr = "leader", type = Type.EXPORT)
//    })
    @TableField(exist = false)
    private SysDept dept;  //部门对象

    @TableField(exist = false)
    private List<SysRole> roles;  //角色对象

    @TableField(exist = false)
    private Long[] roleIds;  //角色组

    @TableField(exist = false)
    private Long[] postIds;  //岗位组

    @TableField(exist = false)
    private Long roleId;  //角色ID
}
