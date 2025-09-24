package org.emilia.tienchin.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.emilia.tienchin.mapper.SysMenuMapper;
import org.emilia.tienchin.pojo.entity.SysMenu;
import org.emilia.tienchin.pojo.entity.SysRole;
import org.emilia.tienchin.pojo.vo.MetaVo;
import org.emilia.tienchin.pojo.vo.RouterVo;
import org.emilia.tienchin.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        for (SysMenu m : topMenus) {
            m.setComponent("Layout");
            m.setPath("/");
//            if (m.getMenuType() .equals("C") ) {
//                m.setPath("/");
//            }else  {
//                m.setPath(m.getPath());
//            }
        }
        return topMenus.stream()
                .map(menu -> convertToVo(menu))
                .collect(Collectors.toList());
    }
    private RouterVo convertToVo(SysMenu menu) {
        RouterVo vo = new RouterVo();
//        vo.setName(menu.getMenuName());
        vo.setPath(menu.getPath());
        vo.setHidden(false);
        vo.setComponent(menu.getComponent());
        vo.setQuery(menu.getQuery());
        vo.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), true, menu.getPath()));
        return vo;
    }
}
