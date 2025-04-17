package com.xw.controller;

import com.xw.common.Constants;
import com.xw.common.Result;
import com.xw.common.TokenHandler;
import com.xw.dao.IconDao;
import com.xw.dao.MenuDao;
import com.xw.dao.RoleMenuDao;
import com.xw.dao.RolesDao;
import com.xw.entity.Menu;
import com.xw.entity.Roles;
import com.xw.entity.User;
import com.xw.exp.ServiceException;
import com.xw.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("menu")
@Tag(name = "菜单模块")
public class MenuController {
    @Resource
    private MenuDao menuDao;

    @Resource
    private MenuService menuService;

    @Resource
    private RoleMenuDao roleMenuDao;

    @Resource
    private RolesDao rolesDao;

    @Resource
    private IconDao iconDao;

    @PostMapping(value = "addMenu")
    @Operation(summary = "菜单新增")
    public Result<?> addMenu(@RequestBody @Validated Menu menu) {
        menuService.addMenu(menu);
        return new Result<>(Constants.SUCCESS);
    }

    @PostMapping(value = "updateMenu")
    @Operation(summary = "菜单修改")
    public Result<?> updateMenu(@RequestBody @Validated Menu menu) {
        if (menu.getId() == null) {
            throw new ServiceException(Constants.ERROR.getCode(), "请选择菜单");
        }
        menuDao.update(menu);
        return new Result<>(Constants.SUCCESS);
    }

    @DeleteMapping(value = "delMenu")
    @Operation(summary = "菜单删除")
    public Result<?> delMenu(@RequestParam Integer menuId) {
        menuService.delMenu(menuId);
        return new Result<>(Constants.SUCCESS);
    }

    @GetMapping(value = "menuList")
    @Operation(summary = "菜单列表")
    public Result<?> menuList() {
        List<Menu> menuList = menuService.menuList();
        return new Result<>(menuList,Constants.SUCCESS);
    }

    @GetMapping(value = "parentMenuList")
    @Operation(summary = "获取父菜单")
    public Result<?> parentMenuList() {
        return new Result<>(menuDao.queryParentMenu(),Constants.SUCCESS);
    }

    @GetMapping(value = "iconList")
    @Operation(summary = "获取图标列表")
    public Result<?> iconList() {
        return new Result<>(iconDao.queryAll(),Constants.SUCCESS);
    }

    @GetMapping(value = "menuChildrenList")
    @Operation(summary = "获取所有子菜单")
    public Result<?> menuChildrenList(@RequestParam(required = false) String menuName) {
        User userInfo = TokenHandler.getUserInfo();
        Roles roles = rolesDao.selectByRoleName(userInfo.getRole());
        List<Integer> menuIds = roleMenuDao.selectByRoleId(roles.getId());
        List<Menu> menuChildrenList = menuDao.menuChildrenList(menuName,menuIds);
        return new Result<>(menuChildrenList,Constants.SUCCESS);
    }
}
