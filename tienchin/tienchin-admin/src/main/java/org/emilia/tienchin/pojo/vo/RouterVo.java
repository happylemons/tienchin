package org.emilia.tienchin.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.emilia.tienchin.pojo.entity.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 路由配置信息
 *
 * @author tienchin
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouterVo {

    private String name;  //路由名字

    private String path;  //路由地址

    private boolean hidden;  //是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现

    private String redirect;  //重定向地址，当设置 noRedirect 的时候该路由在面包屑导航中不可被点击

    private String component;  //组件地址

    private String query;   //路由参数：如 {"id": 1, "name": "ry"}

    private Boolean alwaysShow;  //当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面

    private MetaVo meta;  //其他元素

    private List<RouterVo> children;  //子路由


    public RouterVo(SysMenu sysMenu) {
//        this.name = sysMenu.getMenuName();
        this.path = sysMenu.getPath();
        this.hidden = "1".equals(sysMenu.getVisible());
//        this.redirect = sysMenu.get;
        this.component = sysMenu.getComponent() == null ? "Layout" : sysMenu.getComponent();
        this.query = sysMenu.getQuery();
        this.alwaysShow = true;
        this.meta = toMeta(sysMenu);
//        this.children = sysMenu.getChildren();
    }


    public MetaVo toMeta(SysMenu menu) {
        MetaVo meta = new MetaVo();
        meta.setTitle(menu.getMenuName());
        meta.setIcon(menu.getIcon());
        meta.setNoCache(menu.getIsCache().equals("1"));
        if (menu.getIsFrame().equals("0")) {
            meta.setLink(menu.getPath());
        } else {
            meta.setLink(null);
        }
        return meta;
    }
}