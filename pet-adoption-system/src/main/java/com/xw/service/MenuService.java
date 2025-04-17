package com.xw.service;

import com.xw.entity.Menu;
import com.xw.req.PageRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Menu)表服务接口
 */
public interface MenuService {

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    PageInfo<Menu> queryByPage(PageRequest<Menu> pageRequest);

    void addMenu(Menu menu);

    void delMenu(Integer menuId);

    List<Menu> menuList();

    List<Menu> menuListByRoleName(String roleName);
}
