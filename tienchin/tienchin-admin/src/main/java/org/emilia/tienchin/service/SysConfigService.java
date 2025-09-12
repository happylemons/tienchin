package org.emilia.tienchin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.emilia.tienchin.pojo.sys.SysConfig;

public interface SysConfigService extends IService<SysConfig> {
    boolean captchaOnOff();
}
