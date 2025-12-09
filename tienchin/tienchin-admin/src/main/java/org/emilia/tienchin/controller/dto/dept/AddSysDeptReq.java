package org.emilia.tienchin.controller.dto.dept;

import com.baomidou.mybatisplus.annotation.TableField;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.emilia.tienchin.enums.MenuType;
import org.emilia.tienchin.pojo.entity.SysDept;
import org.emilia.tienchin.pojo.entity.SysMenu;
import org.springframework.beans.BeanUtils;


@Data
public class AddSysDeptReq {

    @NotNull(message = "缺少上级部门ID参数")
    private Long parentId;  //父部门ID

    @NotBlank(message = "部门名称不能为空")
    @Size(min = 0, max = 30, message = "部门名称长度不能超过30个字符")
    private String deptName;  //部门名称


    @Size(min = 0, max = 11, message = "联系电话长度不能超过11个字符")
    private String phone;  //联系电话
    @Email(message = "邮箱格式不正确")

    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    private String email;  //邮箱

    private String status;  //部门状态:0正常,1停用
    private Integer orderNum;  //显示顺序
    private String leader;  //负责人

    public SysDept BuildEntity(){
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(this, sysDept);
        return sysDept;
    }

}
