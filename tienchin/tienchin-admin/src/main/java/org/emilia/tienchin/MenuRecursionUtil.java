package org.emilia.tienchin;

import java.util.ArrayList;
import java.util.List;

public class MenuRecursionUtil {
    // 模拟从数据库或其他数据源获取所有菜单的方法
    public static List<Menu> getAllMenus() {
        List<Menu> menus = new ArrayList<>();
        // 构造测试菜单数据，实际中可从数据库查询
        menus.add(new Menu(1, 0, "M", "首页",null));
        menus.add(new Menu(2, 1, "M", "用户管理",null));
        menus.add(new Menu(3, 2, "N", "用户列表",null));
        menus.add(new Menu(4, 2, "N", "权限设置",null));
        menus.add(new Menu(5, 0, "M", "订单管理",null));
        menus.add(new Menu(6, 5, "N", "订单列表",null));
        return menus;
    }

    // 递归遍历菜单，构建层级结构
    public static List<Menu> traverseMenus() {
        List<Menu> allMenus = getAllMenus();
        List<Menu> topMenus = new ArrayList<>();
        // 抽取顶部菜单（parentId=0）
        for (Menu menu : allMenus) {
            if (menu.getParentId() == 0) {
                topMenus.add(menu);
            }
        }
        // 遍历顶部菜单，递归处理子菜单
        for (Menu topMenu : topMenus) {
            buildSubMenus(topMenu, allMenus);
        }
        return topMenus;
    }

    // 递归构建子菜单
    private static void buildSubMenus(Menu parentMenu, List<Menu> allMenus) {
        List<Menu> subMenus = new ArrayList<>();
        // 查出parentMenu下的所有子Menu
        for (Menu menu : allMenus) {
            if (menu.getParentId().equals(parentMenu.getId())) {
                subMenus.add(menu);
            }
        }
        parentMenu.setSubMenus(subMenus); // 这里需要给Menu类添加subMenus字段和setter
        // 如果Menu.type="M"，继续递归处理其子菜单；否则不继续
        for (Menu subMenu : subMenus) {
            if ("M".equals(subMenu.getType())) {
                buildSubMenus(subMenu, allMenus);
            }
        }
    }

    // 测试方法
    public static void main(String[] args) {
        List<Menu> result = traverseMenus();
        // 这里可以打印结果或进行其他处理
        printMenuTree(result, 0);
    }

    // 打印菜单树，用于测试查看结构
    private static void printMenuTree(List<Menu> menus, int level) {
        String prefix = "  ".repeat(level);
        for (Menu menu : menus) {
            System.out.println(prefix + menu.getName() + "(id:" + menu.getId() + ", type:" + menu.getType() + ")");
            if (menu.getSubMenus() != null) {
                printMenuTree(menu.getSubMenus(), level + 1);
            }
        }
    }
}
