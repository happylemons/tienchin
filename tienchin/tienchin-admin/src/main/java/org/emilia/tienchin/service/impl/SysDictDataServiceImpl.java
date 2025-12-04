package org.emilia.tienchin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.emilia.tienchin.mapper.SysDictDataMapper;
import org.emilia.tienchin.pojo.entity.SysDictData;
import org.emilia.tienchin.service.SysDictDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements SysDictDataService {

    private final SysDictDataMapper sysDictDataMapper;

    @Override
    public List<SysDictData> dictType(String dictType) {
        QueryWrapper<SysDictData> queryWrapper = new QueryWrapper<SysDictData>().eq("dict_type", dictType);
        List<SysDictData> list = sysDictDataMapper.selectList(queryWrapper);

        return list;
    }
}

