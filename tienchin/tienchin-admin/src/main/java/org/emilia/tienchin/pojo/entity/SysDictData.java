package org.emilia.tienchin.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.emilia.tienchin.pojo.BaseEntity;


/**
 * 字典数据表 sys_dict_data
 */
@Data
public class SysDictData extends BaseEntity {
    private static final long serialVersionUID = 1L;

//    @Excel(name = "字典编码", cellType = ColumnType.NUMERIC)
    private Long dictCode;  //字典编码

//    @Excel(name = "字典排序", cellType = ColumnType.NUMERIC)
    private Long dictSort;  //字典排序

//    @Excel(name = "字典标签")
    private String dictLabel;  //字典标签

//    @Excel(name = "字典键值")
    private String dictValue;  //字典键值

//    @Excel(name = "字典类型")
    private String dictType;  //字典类型

    private String cssClass;  //样式属性（其他样式扩展）

    private String listClass;  //表格字典样式

//    @Excel(name = "是否默认", readConverterExp = "Y=是,N=否")
    private String isDefault;  //是否默认（Y是 N否）

//    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;  //状态（0正常 1停用）

}
