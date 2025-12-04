package org.emilia.tienchin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.emilia.tienchin.pojo.entity.SysDictData;

import java.util.List;

public interface SysDictTypeService  extends IService<SysDictData> {

    List<SysDictData> dictType(String dictType);
}
