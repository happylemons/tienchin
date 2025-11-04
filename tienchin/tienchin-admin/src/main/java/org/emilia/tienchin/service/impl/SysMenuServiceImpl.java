package org.emilia.tienchin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.emilia.tienchin.controller.dto.menu.AddSysMenuReq;
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
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.emilia.tienchin.exception.ParamValidException.buildParamException;

@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private final SysMenuMapper sysMenuMapper;
    private final SysUserRoleMapper userRoleMapper;
    private final SysRoleMenuMapper roleMenuMapper;
    private final SysUserMapper sysUserMapper;
    private final SysRoleMapper sysRoleMapper;
    private final Redisson redisson;

    @Autowired
    @Lazy
    private SysMenuService sysMenuService;

    private static final String menuParentLockPrefix = "MENU_PARENT_LOCK_";

    @Override
    public AjaxResult treeselect(Long userId) {

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
    public AjaxResult add(AddSysMenuReq menu) {

        SysMenu sysMenu = menu.buildEntity();
        //1.父菜单是否存在且合法
        validateParentMenu(sysMenu);

        //2.验证菜单类型
        validateMenuTypeFields(sysMenu);
        // 4. 验证路由和组件路径
        validatePathAndComponent(sysMenu);

        // 3. 处理排序号（如果为空则设置默认值）
        RLock lock = redisson.getLock(menuParentLockPrefix + menu.getParentId());
        try {
            lock.lock();
            sysMenuService.saveMenu(sysMenu);
        } finally {
            lock.unlock();
        }
        return AjaxResult.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public SysMenu saveMenu(SysMenu menu) {

        Integer orderNum = menu.getOrderNum();
        if (orderNum == null) {
            //给sysMenu赋一个orderNumber
            SysMenu sysMenu = sysMenuMapper.selectOne(new QueryWrapper<SysMenu>()
                    .select("MAX(order_num) as order_num")
                    .eq("parent_id", menu.getParentId()));
            int maxOrderNum = sysMenu == null ? 1 : sysMenu.getOrderNum() + 1;
            menu.setOrderNum(maxOrderNum);
            sysMenuMapper.insert(menu);
        } else if (checkOrderNumberIsExists(orderNum, menu.getParentId())) {
            throw buildParamException("orderNumber", "在当前目录下" + menu.getParentId() + "已经存在orderNumber" + menu.getOrderNum());
        }
        sysMenuMapper.insert(menu);
        return menu;
    }

    private boolean checkOrderNumberIsExists(Integer orderNum, Long parentId) {
        SysMenu sysMenu = sysMenuMapper.selectOne(new QueryWrapper<SysMenu>()
                .select("1 as order_num")
                .eq("parent_id", parentId)
                .eq("order_num", orderNum));
        return sysMenu != null;
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
            if ("M".equals(menu.getMenuType())) {
                throw buildParamException("parentId", "顶级菜单必须是目录类型");
            }
            return;
        }
        SysMenu parentMenu = getById(parentId);
        if (parentMenu == null && menu.getParentId() != 0) {
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
        checkCircularReference(parentId);

    }

    private void checkCircularReference(Long parentId) {
        if (parentId == 0L) {
            return;
        }
        List<Long> path = new ArrayList<>();
        Long currentParentId = parentId;
        while (true) {
            SysMenu parentMenu = getById(parentId);
            path.add(currentParentId);
            if (parentMenu.getParentId() == 0L) {
                break;
            }
            if (parentMenu.getParentId().equals(currentParentId)) {
                throw buildParamException("parentId", "存在循环引用" + path + "->" + parentId);
            }
            currentParentId = parentMenu.getParentId();
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
        SysMenu sysMenu = menu.buildEntity();
        validateParentMenu(sysMenu);
        validateMenuTypeFields(sysMenu);
        validatePathAndComponent(sysMenu);
        RLock lock = redisson.getLock(menuParentLockPrefix + menu.getParentId());
        try {
            lock.lock();
            sysMenuService.saveMenu(sysMenu);
        } finally {
            lock.unlock();
        }
        return AjaxResult.success();
    }


    @Override
    public SysMenu selectByMenuId(Long menuId) {
        SysMenu menu = sysMenuMapper.selectOne(new QueryWrapper<SysMenu>().eq("menu_id", menuId));
        return menu;
    }

    @Override
    public List<SysMenu> selectMenus(ListSysMenuReq menu) {
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();

        if (menu != null) {
            if (menu.getMenuName() != null) {
                String menuName = menu.getMenuName().replaceAll("^\\s+", "");
                queryWrapper.like("menu_name", menuName);
            }
            if (menu.getStatus() != null) {
                queryWrapper.eq("status", menu.getStatus());
            }
        }
        return sysMenuMapper.selectList(queryWrapper);
    }

    @Override
    public List<RouterVo> getRouters() {
        List<SysMenu> allMenus = sysMenuMapper.findAllMenus().stream().filter(m -> !m.getMenuType().equals("F")).collect(Collectors.toList());
        List<SysMenu> topMenus = allMenus.stream()
                .filter(m -> !Objects.equals(m.getMenuType(), "F") && m.getParentId() == 0L)
                .sorted((m1, m2) -> m1.getOrderNum().compareTo(m2.getOrderNum()))
                .collect(Collectors.toList());

        List<RouterVo> topRouters = new ArrayList<>();
        for (SysMenu topMenu : topMenus) {
            List<RouterVo> children = findChildren(topMenu, allMenus);
            RouterVo finalTopRouter;
            if ("system".equals(topMenu.getPath())) {
                finalTopRouter = new RouterVo();
                finalTopRouter.setName(upCaseFirst(topMenu.getPath()));
                finalTopRouter.setPath("/" + topMenu.getPath());
                finalTopRouter.setComponent("Layout");
                finalTopRouter.setHidden(false);
                finalTopRouter.setRedirect("noRedirect");
                finalTopRouter.setAlwaysShow(true);
                finalTopRouter.setMeta(toMeta(topMenu));
                finalTopRouter.setChildren(children);
            } else {
                RouterVo wrapperRouter = new RouterVo();
                wrapperRouter.setPath("/");
                wrapperRouter.setComponent("Layout");
                wrapperRouter.setHidden(false);
                wrapperRouter.setChildren(new ArrayList<>());
                RouterVo originalTopRouter = new RouterVo();
                if (children.isEmpty()) {
                    originalTopRouter.setName(upCaseFirst(topMenu.getPath()));
                    originalTopRouter.setPath(topMenu.getPath());
                    originalTopRouter.setComponent("Layout");
                    originalTopRouter.setHidden(false);
                    originalTopRouter.setMeta(toMeta(topMenu));
                    originalTopRouter.setChildren(Collections.emptyList());
                } else {
                    originalTopRouter.setName(upCaseFirst(topMenu.getPath()));
                    originalTopRouter.setPath(topMenu.getPath());
                    originalTopRouter.setComponent("Layout");
                    originalTopRouter.setHidden(false);
                    originalTopRouter.setMeta(toMeta(topMenu));
                    originalTopRouter.setChildren(children);
                }

                wrapperRouter.getChildren().add(originalTopRouter);
                finalTopRouter = wrapperRouter;
            }

            topRouters.add(finalTopRouter);
        }

        return topRouters;
    }

    public List<RouterVo> findChildren(SysMenu topMenu, List<SysMenu> allMenus) {
        List<SysMenu> children = allMenus.stream()
                .filter(m -> m.getParentId().equals(topMenu.getMenuId()))
                .sorted((m1, m2) -> m1.getOrderNum().compareTo(m2.getOrderNum()))
                .collect(Collectors.toList());
        return children.stream().map(childMenu -> {
            RouterVo childRouter = new RouterVo();
            childRouter.setName(upCaseFirst(childMenu.getPath()));
            childRouter.setPath(childMenu.getPath());
            childRouter.setHidden(false); // 目标JSON中所有hidden都是false
            childRouter.setMeta(toMeta(childMenu));

            List<RouterVo> grandChildren = findChildren(childMenu, allMenus);
            if (!grandChildren.isEmpty()) {
                childRouter.setRedirect("noRedirect");
                childRouter.setAlwaysShow(true);
                childRouter.setComponent("ParentView");
                childRouter.setChildren(grandChildren);
            } else {
                childRouter.setComponent(childMenu.getComponent());
                childRouter.setChildren(Collections.emptyList());
            }
            return childRouter;
        }).collect(Collectors.toList());
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