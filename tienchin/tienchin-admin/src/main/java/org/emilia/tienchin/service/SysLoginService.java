package org.emilia.tienchin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.pojo.entity.SysUser;
import org.emilia.tienchin.pojo.model.LoginUser;
import org.emilia.tienchin.pojo.sys.SysConfig;
import org.emilia.tienchin.pojo.model.LoginBody;

import jakarta.servlet.http.HttpServletRequest;

public interface SysLoginService extends IService<SysUser> {

    String login(String username,
                 String password,
                 String code,
                 String uuid,
                 HttpServletRequest request);


    LoginUser getInfo();
}
