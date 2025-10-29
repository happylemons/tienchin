package org.emilia.tienchin.controller.dto.user;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.emilia.tienchin.pojo.entity.SysUser;

import java.util.Date;

@Data
public class ListSysUserReq {
    private String userName;
    private String phonenumber;
    private String status;
    private Date beginTime;
    private Date endTime;
}
