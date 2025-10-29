package org.emilia.tienchin.controller.dto.csv;

import com.opencsv.bean.AbstractBeanField;

import java.util.Objects;

public class DataScopeConverter extends AbstractBeanField<String, String> {
    @Override
    protected String convert(String value) {
        if (Objects.isNull(value) || value.trim().isEmpty()) {
            return "未知权限";
        }
        switch (value) {
            case "1":
                return "全部数据权限";
            case "2" :
                return "自定义数据权限";
            case "3":
                return "本部门数据权限";
            case "4":
                return "本部门及以下数据权限";
            case "5":
                return "仅本人数据权限";
            default:
                return null;
        }

    }
    protected String deConvert(String value) {
       
        switch (value) {
            case "全部数据权限":
                return "1";
            case "自定义数据权限" :
                return "2";
            case "本部门数据权限":
                return "3";
            case "本部门及以下数据权限":
                return "4";
            case "仅本人数据权限":
                return "5";
            default:
                return null;
        }
    }
}