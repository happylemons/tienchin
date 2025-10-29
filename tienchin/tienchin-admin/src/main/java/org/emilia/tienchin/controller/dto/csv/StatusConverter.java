package org.emilia.tienchin.controller.dto.csv;

import com.opencsv.bean.AbstractBeanField;

import java.util.Objects;

public class StatusConverter extends AbstractBeanField<String, String> {
    @Override
    protected String convert(String value) {
        if (Objects.isNull(value) || value.trim().isEmpty()) {
            return "未知状态";
        }
        switch (value) {
            case "0":
                return "正常";
            case "1":
                return "停用";
            default:
                return null;
        }
    }
}