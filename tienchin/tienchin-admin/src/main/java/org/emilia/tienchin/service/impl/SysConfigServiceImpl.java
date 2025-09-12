package org.emilia.tienchin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.emilia.tienchin.mapper.SysConfigMapper;
import org.emilia.tienchin.pojo.sys.SysConfig;
import org.emilia.tienchin.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {
    @Autowired(required = false)
    private SysConfigMapper sysConfigMapper;

    @Override
    public boolean captchaOnOff() {
        SysConfig sysConfig = sysConfigMapper.selectOne(new QueryWrapper<SysConfig>().eq("config_id", 4));

        String configValue = sysConfig.getConfigValue();
        return Objects.equals(configValue, "false");
    }
}