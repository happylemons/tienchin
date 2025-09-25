package org.emilia.tienchin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.emilia.tienchin.mapper.SysMenuMapper;
import org.emilia.tienchin.pojo.entity.SysMenu;
import org.emilia.tienchin.pojo.vo.MetaVo;
import org.emilia.tienchin.pojo.vo.RouterVo;
import org.emilia.tienchin.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<RouterVo> getRouters() {

        List<SysMenu> allMenus = sysMenuMapper.findAllMenus();

        List<SysMenu> topMenus = allMenus.stream()
                .filter(m -> m.getParentId() == 0)
                .sorted((Comparator.comparingInt(SysMenu::getOrderNum)))
                .collect(Collectors.toList());

        //遍历整个顶部Menu
//        for (SysMenu m : topMenus) {
//            m.setComponent("Layout");
//            m.setPath("/");
//           if (m.getMenuType() .equals("C") ) {
//              m.setPath("/");
//           }else  {
//              m.setPath(m.getPath());
//            }
//        }
        List<RouterVo> result = topMenus.stream()
                .map(menu -> convertToVo(menu, allMenus))
                .collect(Collectors.toList());
        return result;
    }

    private RouterVo convertToVo(SysMenu menu, List<SysMenu> allMenus) {
        RouterVo vo = new RouterVo();
        if (menu.getMenuId() == 1L) {
            vo.setPath("/" + menu.getPath());
            List<SysMenu> systemMenus = allMenus.stream().filter(m -> m.getParentId() == 1)
                    .sorted((Comparator.comparingInt(SysMenu::getOrderNum)))
                    .collect(Collectors.toList());
            vo.setName(upCaseFirst(menu.getPath()));
            vo.setRedirect("noRedirect");
            vo.setAlwaysShow(true);
            vo.setMeta(toMeta(menu));
            ArrayList<RouterVo> child = new ArrayList<>();
            for (SysMenu m : systemMenus) {
                RouterVo c = toChildren(m);
                child.add(c);
            }
            vo.setChildren(child);
        } else if (menu.getMenuId() == 4L) {
            vo.setPath(menu.getPath());
            String str = menu.getPath();
            vo.setName(upCaseFirst(str));
            vo.setMeta(toMeta(menu));
        } else {
            vo.setPath("/");
            ArrayList<RouterVo> child = new ArrayList<>();
            RouterVo c = toChildren(menu);
            child.add(c);
            vo.setChildren(child);
        }
        vo.setHidden(false);
        vo.setComponent("Layout");
        vo.setQuery(menu.getQuery());
        return vo;
    }

    public RouterVo toChildren(SysMenu menu) {
        RouterVo c = new RouterVo();
        c.setName(upCaseFirst(menu.getPath()));
        c.setPath(menu.getPath());
        c.setHidden(false);
        c.setComponent(menu.getComponent());
        c.setMeta(toMeta(menu));
        return c;
    }

    public String upCaseFirst(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);

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
