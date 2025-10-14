package org.emilia.tienchin.config;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.beans.ConstructorProperties;

@Data
//@NoArgsConstructor
public class PageImpl<T> extends PageDTO<T> {

    private int pageNum;
    private int pageSize;
    private String orderByColumn;
    private int isAsc;
    private int reasonable;

    @ConstructorProperties(value = {"pageNum","pageSize","orderByColumn","isAsc","reasonable"})
    public PageImpl(Long pageNum, Long pageSize, String orderByColumn, Boolean isAsc, Integer reasonable) {
        super(pageNum == null ? 1:pageNum,
                pageSize == null ? 10:pageSize);
        if (StringUtils.isNoneBlank(orderByColumn)){
            OrderItem o = new OrderItem();
            o.setColumn(orderByColumn);
            if (isAsc != null && isAsc){
                o.setAsc(true);
            }else if (isAsc != null){
                o.setAsc(false);
            }else {
                o.setAsc(true);
            }
            super.addOrder(o);
        }
    }



}
