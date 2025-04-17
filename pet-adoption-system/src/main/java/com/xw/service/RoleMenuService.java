package com.xw.service;

import com.xw.entity.RoleMenu;
import com.xw.req.PageRequest;
import com.github.pagehelper.PageInfo;

/**
 * (RoleMenu)表服务接口
 */
public interface RoleMenuService {

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    PageInfo<RoleMenu> queryByPage(PageRequest<RoleMenu> pageRequest);

}
