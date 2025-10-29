package org.emilia.tienchin.controller.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.emilia.tienchin.pojo.entity.SysDept;
import org.emilia.tienchin.pojo.entity.SysRole;
import org.emilia.tienchin.pojo.entity.SysUser;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserCsvDto {

    @CsvBindByName(column = "用户ID")
//    @CsvBindByPosition(position = 0)
    private Long userId;  //用户ID
    //    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "部门ID")
    private Long deptId;  //部门ID

    @CsvBindByName(column = "用户账号")
    @NotBlank
    private String userName;  //用户账号

    @CsvBindByName(column = "用户昵称")
    private String nickName;  //用户昵称

    @CsvBindByName(column = "用户邮箱")
    private String email;  //用户邮箱

    @CsvBindByName(column = "手机号码")
    private String phonenumber;  //手机号码

    @CsvBindByName(column = "用户性别")
    private String sex;  //用户性别

    @CsvBindByName(column = "用户头像")
    private String avatar;  //用户头像

    @CsvBindByName(column = "密码")
    private String password;  //密码

    @CsvBindByName(column = "盐加密")
    private String salt;  //盐加密

    @CsvBindByName(column = "帐号状态（0正常 1停用）")
    private String status;  //帐号状态（0正常 1停用）

    @CsvBindByName(column = "删除标志（0代表存在 2代表删除）")
    private String delFlag;  //删除标志（0代表存在 2代表删除）

    @CsvBindByName(column = "最后登录IP")
    private String loginIp;  //最后登录IP

    @CsvBindByName(column = "最后登录时间")
    @CsvDate(value = "yyyy-MM-dd HH:mm:ss")  // 日期格式化（根据实际格式调整）
    private Date loginDate;  //最后登录时间

    public SysUserCsvDto(SysUser sysUser) {
        BeanUtils.copyProperties(sysUser, this);
    }

    public SysUser toSysUser(SysUserCsvDto dto) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(dto, user);
        return user;
    }
}
