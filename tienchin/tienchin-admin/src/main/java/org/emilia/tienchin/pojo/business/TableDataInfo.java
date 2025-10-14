package org.emilia.tienchin.pojo.business;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 *
 * @author tienchin
 */
@Data
public class TableDataInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private long total;              //总记录数
    private List<?> rows;            //列表数据
    private int code;               //消息状态码
    private String msg;             //消息内容

    /**
     * 表格数据对象
     */
    public TableDataInfo() {
    }

    /**
     * 分页
     * @param list  列表数据
     * @param total 总记录数
     */
    public TableDataInfo(List<?> list, int total) {
        this.rows = list;
        this.total = total;
    }

}
