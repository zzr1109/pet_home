package com.xw.service.impl;

import com.xw.common.Constants;
import com.xw.dao.RoleMenuDao;
import com.xw.dao.RolesDao;
import com.xw.entity.Menu;
import com.xw.dao.MenuDao;
import com.xw.entity.RoleMenu;
import com.xw.entity.Roles;
import com.xw.exp.ServiceException;
import com.xw.service.MenuService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xw.req.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import jakarta.annotation.Resource;

/**
 * (Menu)表服务实现类
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuDao menuDao;

    @Resource
    private RoleMenuDao roleMenuDao;

    @Resource
    private RolesDao rolesDao;

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @Override
    public PageInfo<Menu> queryByPage(PageRequest<Menu> pageRequest) {
        //开始分页
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<Menu> list = this.menuDao.queryAll(pageRequest.getQ());
        PageInfo<Menu> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void addMenu(Menu menu) {
        // 判断子菜单内容的传递是否符合规范
        matching(menu);
        // 判断数据库里面是否存在相同的菜单名称
        Menu menuQueryByMenuName = new Menu();
        menuQueryByMenuName.setMenuName(menu.getMenuName());
        Menu menuNameData = menuDao.queryByAll(menuQueryByMenuName);
        if (Objects.nonNull(menuNameData)) {
            throw new ServiceException(Constants.ERROR.getCode(),"该菜单已存在,请别重复新增");
        }
        menuDao.insert(menu);
    }

    @Override
    public void delMenu(Integer menuId) {
        // 1 首先要判断该菜单是不是父菜单
        Menu menu = menuDao.queryById(menuId);
        if (Objects.isNull(menu)) {
            throw new ServiceException(Constants.ERROR.getCode(), "找不到该菜单");
        }
        List<Integer> delIds = new ArrayList<>();
        delIds.add(menuId);
        // 父菜单
        if (menu.getParentId() == null) {
            Menu menuQuery = new Menu();
            menuQuery.setParentId(menuId);
            // 查询该父菜单下的所有子菜单
            List<Menu> menus = menuDao.queryAll(menuQuery);
            if (!CollectionUtils.isEmpty(menus)) {
                delIds.addAll(menus.stream().map(Menu::getId).distinct().collect(Collectors.toList()));
            }
        }
        menuDao.deleteBatchByIds(delIds);

        // 把该菜单与角色的关联表进行删除
        RoleMenu roleMenuQuery = new RoleMenu();
        roleMenuQuery.setMenuIds(delIds);
        List<RoleMenu> roleMenus = roleMenuDao.queryAll(roleMenuQuery);
        if (!CollectionUtils.isEmpty(roleMenus)) {
            roleMenuDao.deleteBatchByIds(roleMenus.stream().map(RoleMenu::getId).collect(Collectors.toList()));
        }
    }

    @Override
    public List<Menu> menuList() {
        List<Menu> parentMenuList = new ArrayList<>();
        // 1 查询所有的菜单
        List<Menu> menus = menuDao.queryAll();
        // 2 筛选出所有父菜单
        List<Menu> parentMenus = menus.stream().filter(u -> u.getParentId() == null).collect(Collectors.toList());
        for (Menu parentMenu : parentMenus) {
            recursionMenu(parentMenu,menus);
        }
        // 数据设置完了之后，开始排序数据
        parentMenus = parentMenus.stream().sorted(Comparator.comparing(Menu::getSort)).collect(Collectors.toList());
        for (Menu parentMenu : parentMenus) {
            parentMenu.setChildren(parentMenu.getChildren().stream().sorted(Comparator.comparing(Menu::getSort)).collect(Collectors.toList()));
        }
        return parentMenus;
    }

    @Override
    public List<Menu> menuListByRoleName(String roleName) {
        List<Menu> menus = new ArrayList<>();
        Roles roles = new Roles();
        roles.setName(roleName);
        Roles rolesQuery = rolesDao.queryByAll(roles);
        if (Objects.nonNull(rolesQuery)) {
            RoleMenu roleMenuQuery = new RoleMenu();
            roleMenuQuery.setRoleId(rolesQuery.getId());
            List<RoleMenu> roleMenus = roleMenuDao.queryAll(roleMenuQuery);
            List<Integer> roleByMenuIds = roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());

            Menu menuQuery = new Menu();
            menuQuery.setMenuIds(roleByMenuIds);
            List<Menu> dataList = menuDao.queryAll(menuQuery);
            List<Menu> parentMenuList = dataList.stream().filter(u -> u.getParentId() == null).collect(Collectors.toList());
            for (Menu parentMenu : parentMenuList) {
                recursionMenu(parentMenu,dataList);
            }
            menus = parentMenuList.stream().sorted(Comparator.comparing(Menu::getSort)).collect(Collectors.toList());
            for (Menu parentMenu : menus) {
                parentMenu.setChildren(parentMenu.getChildren().stream().sorted(Comparator.comparing(Menu::getSort)).collect(Collectors.toList()));
            }
        }
        return menus;
    }

    // 开始递归设置数据
    private void recursionMenu(Menu parentMenu,List<Menu> menus) {
        List<Menu> childrenMenus = menus.stream()
                .filter(u -> u.getParentId() != null)
                .filter(u -> u.getParentId().compareTo(parentMenu.getId()) == 0)
                .collect(Collectors.toList());
        parentMenu.setChildren(childrenMenus);
        for (Menu childrenMenu : childrenMenus) {
            recursionMenu(childrenMenu,menus);
        }
    }

    private void matching(Menu menu) {
        if (menu.getParentId() != null) {
            if (!StringUtils.hasText(menu.getFilePath())) {
                throw new ServiceException(Constants.ERROR.getCode(), "请填写Vue文件所在的位置");
            }
            if (!StringUtils.hasText(menu.getPath())) {
                throw new ServiceException(Constants.ERROR.getCode(), "请填写跳转路径");
            }
            if (!StringUtils.hasText(menu.getName())) {
                throw new ServiceException(Constants.ERROR.getCode(), "请填写路由名称");
            }
            // 查询一下 该 parentId 的数据 是否还包含 parentId, 如果包含 则 抛出自定义异常
            Menu upMenu = menuDao.queryById(menu.getParentId());
            if (Objects.isNull(upMenu)) {
                throw new ServiceException(Constants.ERROR.getCode(), "父菜单不能为空");
            }
            if (upMenu.getParentId() != null) {
                throw new ServiceException(Constants.ERROR.getCode(), "只允许新增2级菜单");
            }
        }
    }
}
