package com.xw.service.impl;

import com.xw.entity.UserRole;
import com.xw.dao.UserRoleDao;
import com.xw.service.UserRoleService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xw.req.PageRequest;

import java.util.List;

import jakarta.annotation.Resource;

/**
 * (UserRole)表服务实现类
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleDao userRoleDao;

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @Override
    public PageInfo<UserRole> queryByPage(PageRequest<UserRole> pageRequest) {
        //开始分页
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<UserRole> list = this.userRoleDao.queryAll(pageRequest.getQ());
        PageInfo<UserRole> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
