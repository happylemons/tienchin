package org.emilia.tienchin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
class Menu {
    private Integer id;
    private Integer parentId;
    private String type; // 用于判断是否为可展开的菜单，这里"M"表示可展开
    private String name; // 菜单名称，可选字段，用于标识菜单
    private List<Menu> subMenus;
}
