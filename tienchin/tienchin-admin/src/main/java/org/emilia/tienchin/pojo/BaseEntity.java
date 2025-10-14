package org.emilia.tienchin.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

///Entity基类
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String searchValue;  //搜索值

    @TableField(exist = false)
    private String createBy;  //创建者

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;  //创建时间

    private String updateBy;  //更新者

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date updateTime;  //更新时间

    private String remark;  //备注

    @TableField(exist = false)
    private HashMap<String, Object> params;  //请求参数

}
