package org.emilia.tienchin.pojo.sys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 参数配置表 sys_config
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysConfig  {
    private static final long serialVersionUID = 1L;
    private Long configId;
    private String configName;
    private String configKey;
    private String configValue;
    private String configType;
}
