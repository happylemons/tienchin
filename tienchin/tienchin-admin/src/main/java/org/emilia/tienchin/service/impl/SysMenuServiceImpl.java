package org.emilia.tienchin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.emilia.tienchin.controller.dto.menu.EditSysMenuReq;
import org.emilia.tienchin.controller.dto.menu.ListSysMenuReq;
import org.emilia.tienchin.mapper.*;
import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.pojo.business.TreeSelect;
import org.emilia.tienchin.pojo.entity.SysMenu;
import org.emilia.tienchin.pojo.entity.SysRole;
import org.emilia.tienchin.pojo.entity.SysUser;
import org.emilia.tienchin.pojo.sys.SysRoleMenu;
import org.emilia.tienchin.pojo.sys.SysUserRole;
import org.emilia.tienchin.pojo.vo.MetaVo;
import org.emilia.tienchin.pojo.vo.RouterVo;
import org.emilia.tienchin.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.emilia.tienchin.exception.ParamValidException.buildParamException;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysRoleMenuMapper roleMenuMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;


    @Override
    public AjaxResult treeselect(SysMenu menu, Long userId) {

        SysUser sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("user_id", userId));
        if (sysUser == null) {
            return AjaxResult.error("用户不存在");
        }
        if (userId == 1L) {
            List<SysMenu> allMenus = sysMenuMapper.findAllMenus();
            List<TreeSelect> treeMenus = buildTreeMenus(allMenus, 0L);
            System.out.println("treeMenus.size = " + treeMenus.size());
            return AjaxResult.success(treeMenus);
        } else {
            List<SysUserRole> ur = userRoleMapper.selectList(new QueryWrapper<SysUserRole>().eq("user_id", userId));
            List<Long> roleIds = ur.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
            ArrayList<SysMenu> allMenus = new ArrayList<>();
            for (Long roleId : roleIds) {
                List<SysRoleMenu> roles = roleMenuMapper.selectList(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
                List<Long> menuIds = roles.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
                for (Long menuId : menuIds) {
                    SysMenu roleMenu = sysMenuMapper.selectOne(new QueryWrapper<SysMenu>().eq("menu_id", menuId));
                    allMenus.add(roleMenu);
                }
            }
            List<TreeSelect> treeMenus = buildTreeMenus(allMenus, 0L);
            return AjaxResult.success(treeMenus);
        }
    }

    @Override
    public List<TreeSelect> selectMenusByUserId(Long userId) {
        SysUser sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("user_id", userId));
        if (sysUser == null) {
            return null;
        }
        if (userId == 1L) {
            List<SysMenu> allMenus = sysMenuMapper.findAllMenus();
            List<TreeSelect> treeMenus = buildTreeMenus(allMenus, 0L);
            return treeMenus;
        } else {
            List<SysUserRole> ur = userRoleMapper.selectList(new QueryWrapper<SysUserRole>().eq("user_id", userId));
            List<Long> roleIds = ur.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
            ArrayList<SysMenu> allMenus = new ArrayList<>();
            for (Long roleId : roleIds) {
                List<SysRoleMenu> roles = roleMenuMapper.selectList(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
                List<Long> menuIds = roles.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
                for (Long menuId : menuIds) {
                    SysMenu roleMenu = sysMenuMapper.selectOne(new QueryWrapper<SysMenu>().eq("menu_id", menuId));
                    allMenus.add(roleMenu);
                }
            }
            List<TreeSelect> treeMenus = buildTreeMenus(allMenus, 0L);
            return treeMenus;
        }
    }

    @Override
    public List<Long> checkedKeys(Long roleId) {
        SysRole role = sysRoleMapper.selectOne(new QueryWrapper<SysRole>().eq("role_id", roleId));
        if (role == null) {
            return null;
        }
        List<SysRoleMenu> rm = roleMenuMapper.selectList(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
        List<Long> menuIds = rm.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        return menuIds;
    }

    private List<TreeSelect> buildTreeMenus(List<SysMenu> allMenus, Long parentId) {
        ArrayList<TreeSelect> treeMenus = new ArrayList<>();
        for (SysMenu menu : allMenus) {
            if (menu.getParentId().equals(parentId)) {
                TreeSelect treeMenu = new TreeSelect();
                treeMenu.setId(menu.getMenuId());
                treeMenu.setLabel(menu.getMenuName());
                // 递归查找子菜单
                List<TreeSelect> children = buildTreeMenus(allMenus, menu.getMenuId());
                treeMenu.setChildren(children);
                treeMenus.add(treeMenu);
            }
        }
        return treeMenus;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult removeByMenuId(Long menuId) {
        SysMenu menu = getById(menuId);
        if (menu == null) {
            return AjaxResult.error("MenuId不存在:" + menuId);
        }

        List<SysMenu> sysMenus = sysMenuMapper.selectList(new QueryWrapper<SysMenu>().eq("parent_id", menuId));
        if (sysMenus != null && !sysMenus.isEmpty()) {
            return AjaxResult.error("该菜单存在子菜单，请先删除子菜单");
        }

        int r = sysMenuMapper.deleteById(menuId);
        return r > 0 ? AjaxResult.success() : AjaxResult.error("删除失败！");
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult add(SysMenu menu) {
        Long menuId = menu.getMenuId();
        SysMenu sys = getById(menuId);
        if (sys != null) {
            return AjaxResult.error("MenuId：" + menuId + "已存在");
        }
        Long parentId = menu.getParentId();
        if (parentId.equals(menu.getMenuId())) {
            return AjaxResult.error("不能选择自身作为父菜单");
        }

        //1.父菜单是否存在且合法
        validateParentMenu(menu);

        //2.验证菜单类型
        validateMenuTypeFields(menu);
        // 4. 验证路由和组件路径
        validatePathAndComponent(menu);

        // 3. 处理排序号（如果为空则设置默认值）
        handleOrderNum(menu);

        int insert = sysMenuMapper.insert(menu);
        return insert > 0 ? AjaxResult.success() : AjaxResult.error("故障！");
    }

    private void handleOrderNum(SysMenu menu) {
        Integer maxOrder = sysMenuMapper.selectOne(new QueryWrapper<SysMenu>()
                        .select("MAX(order_num) as order_num")
                        .eq("parent_id", menu.getParentId()))
                .getOrderNum();
        Integer order = menu.getOrderNum();
        if (order < 0) {
            AjaxResult.error("请输入正数");
            return;
        }
        Integer orderNum = 0;
        if (order == null) {
            //前端没有传递order的值
            if (maxOrder == null) {
                orderNum = 1;
            } else {
                orderNum = maxOrder + 1;
            }
            menu.setOrderNum(orderNum);
        } else {
            // 前端传递了order的值
            orderNum = order;
            if (maxOrder == null) {
                orderNum = order;
                menu.setOrderNum(orderNum);
            }
            menu.setOrderNum(orderNum);
            int r = sysMenuMapper.updateOrderNumBatch(menu.getParentId(), order);
            if (r > 0) {
                AjaxResult.success("已更新菜单顺序");
                return;
            }
        }
    }

    private void validateMenuTypeFields(SysMenu menu) {
        String menuType = menu.getMenuType();
        // 按钮(F)不需要路径、组件和图标
        if ("F".equals(menuType)) {
            if (StringUtils.hasText(menu.getPath())) {
                AjaxResult.error("按钮不需要路由地址");
                return;
            }
            if (StringUtils.hasText(menu.getComponent())) {
                AjaxResult.error("按钮不需要组件路径");
                return;
            }
        }
        // 菜单(C)需要路径和组件
        else if ("C".equals(menuType)) {
            if (!StringUtils.hasText(menu.getPath())) {
                AjaxResult.error("菜单必须设置路由地址");
                return;
            }
            if (!StringUtils.hasText(menu.getComponent())) {
                AjaxResult.error("菜单必须设置组件路径");
                return;
            }
        }
    }

    private void validatePathAndComponent(SysMenu sysMenu) {
        // 如果不是按钮类型且有路径，才需要验证
        if ("F".equals(sysMenu.getMenuType()) || !StringUtils.hasText(sysMenu.getPath())) {
            return;
        }

        // 非外链（1是内部路由）
        if ("1".equals(sysMenu.getIsFrame())) {
            if (!sysMenu.getPath().startsWith("/")) {
                AjaxResult.error("内部路由地址必须以'/'开头");
                return;
            }
            // 检查路径中是否包含非法字符
            if (sysMenu.getPath().contains("..") || sysMenu.getPath().contains(";")) {
                AjaxResult.error("路由地址包含非法字符");
                return;
            }
        }
        // 外链（0是外部链接）
        else if ("0".equals(sysMenu.getIsFrame())) {
            if (!sysMenu.getPath().startsWith("http://") && !sysMenu.getPath().startsWith("https://")) {
                AjaxResult.error("外链地址必须以'http://'或'https://'开头");
                return;
            }
        }
    }

    private void validateParentMenu(SysMenu menu) {
        Long parentId = menu.getParentId();
        if (parentId == 0) {
            if (!"M".equals(menu.getMenuType())) {
                throw buildParamException("parentId", "顶级菜单必须是目录类型");
            }
        }
        SysMenu parentMenu = getById(parentId);
        if (parentMenu == null) {
            throw buildParamException("parentId", "父菜单不存在");
        }
        String current = menu.getMenuType();
        String parent = parentMenu.getMenuType();
        if ("F".equals(current) && !"C".equals(parent)) {
            throw buildParamException("parentId", "按钮的父菜单必须是菜单类型(C)");

        }
        if ("C".equals(current) && "F".equals(parent)) {
            throw buildParamException("parentId", "菜单的父菜单不能是按钮类型(F)");
        }
        // 检查是否存在循环引用（父菜单是当前菜单的子菜单）
        checkCircularReference(menu.getMenuId(), parentId);

    }

    private void checkCircularReference(Long currentMenuId, Long parentId) {
        Long tempParentId = parentId;
        while (tempParentId != 0) {
            SysMenu parent = getById(tempParentId);
            if (parent == null) {
                break;
            }
            if (currentMenuId.equals(tempParentId)) {
                throw buildParamException("parentId", "菜单存在循环引用，请重新选择父菜单");
            }
            tempParentId = parent.getParentId();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult edit(EditSysMenuReq menu) {
        //1.根据menuId找到原本的值
        SysMenu old = sysMenuMapper.selectById(menu.getMenuId());
        if (old == null) {
            throw buildParamException("menuId", "修改的菜单不存在");
        }

        //2.检测parentId
        Long parentId = menu.getParentId();
        if (parentId.equals(menu.getMenuId())) {
            throw buildParamException("parentId", "不能选择自身作为父菜单");
        }

        SysMenu current = menu.buildEntity();
        validateParentMenu(current);
        validateMenuTypeFields(current);
        validatePathAndComponent(current);
        handleOrderNum(current);

        int update = sysMenuMapper.updateById(current);
        return update > 0 ? AjaxResult.success() : AjaxResult.error("修改错误，找不到对应的菜单id");
    }


    @Override
    public SysMenu selectByMenuId(Long menuId) {
        SysMenu menu = sysMenuMapper.selectOne(new QueryWrapper<SysMenu>().eq("menu_id", menuId));
        return menu;
    }

    @Override
    public List<SysMenu> selectMenus(ListSysMenuReq menu) {
        return sysMenuMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<RouterVo> getRouters() {
        List<SysMenu> allMenus = sysMenuMapper.findAllMenus();
        List<SysMenu> topMenus = allMenus.stream()
                .filter(m -> !Objects.equals(m.getMenuType(), "F") && m.getParentId() == 0L)
                .sorted((m1, m2) -> m1.getOrderNum().compareTo(m2.getOrderNum()))
                .collect(Collectors.toList());
        List<RouterVo> routerVos = convertMenusToRouterVo(topMenus, allMenus);
        System.out.println("routerVos = " + routerVos);
        return routerVos;
    }


    public List<RouterVo> convertMenusToRouterVo(List<SysMenu> topMenus, List<SysMenu> allMenus) {
        ArrayList<RouterVo> result = new ArrayList<>();
        for (SysMenu menu : topMenus) {
            if (menu.getMenuId() != 1L) {
                RouterVo top = new RouterVo();
                top.setPath("/");
                top.setHidden(false);
                top.setComponent("Layout");
                ArrayList<RouterVo> child = new ArrayList<>();
                RouterVo c = new RouterVo();
                c.setName(upCaseFirst(menu.getPath()));
                c.setPath(menu.getPath());
                c.setHidden(false);
                c.setComponent(menu.getComponent());
                c.setMeta(toMeta(menu));
                child.add(c);
                top.setChildren(child);
                result.add(top);
            }
        }
        return result;
    }

    public RouterVo systemToRouterVo(SysMenu menu, List<SysMenu> allMenus) {
        RouterVo sysTop = new RouterVo();
        sysTop.setName(upCaseFirst(menu.getPath()));
        sysTop.setPath("/" + menu.getPath());
        sysTop.setHidden(false);
        sysTop.setRedirect("noRRedirect");
        sysTop.setAlwaysShow(true);
        sysTop.setComponent("Layout");
        sysTop.setMeta(toMeta(menu));
        List<RouterVo> vos = new ArrayList<>();

        vos = findByParentId(menu.getParentId(), allMenus);
        sysTop.setChildren(vos);
        return sysTop;
    }

    private List<RouterVo> findByParentId(Long parentId, List<SysMenu> allMenus) {
        List<SysMenu> collect = allMenus.stream()
                .filter(m -> m.getParentId().equals(parentId))
                .sorted((m1, m2) -> m1.getOrderNum().compareTo(m2.getOrderNum()))
                .collect(Collectors.toList());
        return null;

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