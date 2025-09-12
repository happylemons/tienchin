package org.emilia.tienchin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.pojo.entity.SysUser;
import org.emilia.tienchin.pojo.sys.SysConfig;
import org.emilia.tienchin.pojo.model.LoginBody;

public interface SysLoginService extends IService<SysUser> {

    AjaxResult login(LoginBody loginBody);

}
