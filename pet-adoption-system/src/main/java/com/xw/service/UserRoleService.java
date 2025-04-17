package com.xw.service;

import com.xw.entity.UserRole;
import com.xw.req.PageRequest;
import com.github.pagehelper.PageInfo;

/**
 * (UserRole)表服务接口
 */
public interface UserRoleService {

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    PageInfo<UserRole> queryByPage(PageRequest<UserRole> pageRequest);

}
