package org.emilia.tienchin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.emilia.tienchin.pojo.entity.SysUser;
import org.emilia.tienchin.pojo.sys.SysConfig;

public interface SysUserService {

    SysUser findUser(String username);

    void updateUserProfile(SysUser user);
}
