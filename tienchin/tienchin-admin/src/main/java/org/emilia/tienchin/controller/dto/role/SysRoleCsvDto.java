package org.emilia.tienchin.controller.dto.role;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvCustomBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.emilia.tienchin.controller.dto.csv.DataScopeConverter;
import org.emilia.tienchin.pojo.entity.SysRole;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleCsvDto {
    @CsvBindByName(column = "角色序号")
    private Long roleId;
    @CsvBindByName(column = "角色名称")
    private String roleName;
    @CsvBindByName(column = "角色权限")
    private String roleKey;
    @CsvBindByName(column = "角色排序")
    private String roleSort;
//    @CsvBindByName(column = "数据范围")
//    @CsvCustomBindByPosition(converter = DataScopeConverter.class, position = 0)
    @CsvCustomBindByName(column = "数据范围", converter = DataScopeConverter.class)
    private String dataScope;
    @CsvBindByName(column = "角色状态")
    private String status;

    public SysRoleCsvDto(SysRole sysRole) {
        BeanUtils.copyProperties(sysRole, this);
    }
}
