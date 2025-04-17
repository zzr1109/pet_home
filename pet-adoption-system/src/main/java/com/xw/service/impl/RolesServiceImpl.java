package com.xw.service.impl;

import com.xw.common.Constants;
import com.xw.dao.RoleMenuDao;
import com.xw.entity.Menu;
import com.xw.entity.RoleMenu;
import com.xw.entity.Roles;
import com.xw.dao.RolesDao;
import com.xw.exp.ServiceException;
import com.xw.rep.RoleByMenuRep;
import com.xw.req.AddRoleMenuReq;
import com.xw.service.MenuService;
import com.xw.service.RolesService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xw.req.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.annotation.Resource;

/**
 * (Roles)表服务实现类
 */
@Service("rolesService")
public class RolesServiceImpl implements RolesService {
    @Resource
    private RolesDao rolesDao;

    @Resource
    private MenuService menuService;

    @Resource
    private RoleMenuDao roleMenuDao;

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @Override
    public PageInfo<Roles> queryByPage(PageRequest<Roles> pageRequest) {
        //开始分页
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<Roles> list = this.rolesDao.queryAll(pageRequest.getQ());
        PageInfo<Roles> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public RoleByMenuRep selectRoleByMenu(Integer roleId) {
        List<Integer> checkedKeys = roleMenuDao.selectByRoleId(roleId);
        if (!CollectionUtils.isEmpty(checkedKeys)) {
            checkedKeys = checkedKeys.stream().distinct().collect(Collectors.toList());
        } else {
            checkedKeys = new ArrayList<>();
        }
        List<Menu> menus = menuService.menuList();
        RoleByMenuRep roleByMenuRep = new RoleByMenuRep();
        roleByMenuRep.setMenus(menus);
        // 这里只放入子菜单
        for (Menu menu : menus) {
            Optional<Integer> any = checkedKeys.stream().filter(u -> u.compareTo(menu.getId()) == 0).findAny();
            if (any.isPresent() && menu.getParentId() == null && !CollectionUtils.isEmpty(menu.getChildren())) {
                checkedKeys.remove(any.get());
            }
        }
        roleByMenuRep.setCheckedKeys(checkedKeys);
        return roleByMenuRep;
    }

    @Override
    @Transactional
    public void addRoleMenu(AddRoleMenuReq addRoleMenuReq) {
        if (CollectionUtils.isEmpty(addRoleMenuReq.getMenuIds())) {
            throw new ServiceException(Constants.ERROR.getCode(),"请先选择菜单！");
        }
        List<Integer> roleMenuIds = roleMenuDao.selectByRoleId(addRoleMenuReq.getRoleId());
        if (!CollectionUtils.isEmpty(roleMenuIds)) {
            // 通过角色ID删除所有的角色与菜单的关联
            roleMenuDao.deleteByRoleId(addRoleMenuReq.getRoleId());
        }
        // 开始新增角色与菜单的关系
        for (Integer menuId : addRoleMenuReq.getMenuIds()) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(addRoleMenuReq.getRoleId());
            roleMenu.setMenuId(menuId);
            roleMenuDao.insert(roleMenu);
        }
    }
}
