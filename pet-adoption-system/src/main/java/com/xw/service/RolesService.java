package com.xw.service;

import com.xw.entity.Roles;
import com.xw.rep.RoleByMenuRep;
import com.xw.req.AddRoleMenuReq;
import com.xw.req.PageRequest;
import com.github.pagehelper.PageInfo;

/**
 * (Roles)表服务接口
 */
public interface RolesService {

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    PageInfo<Roles> queryByPage(PageRequest<Roles> pageRequest);

    RoleByMenuRep selectRoleByMenu(Integer roleId);

    void addRoleMenu(AddRoleMenuReq addRoleMenuReq);
}
