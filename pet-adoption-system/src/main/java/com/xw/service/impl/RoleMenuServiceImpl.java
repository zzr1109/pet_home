package com.xw.service.impl;

import com.xw.entity.RoleMenu;
import com.xw.dao.RoleMenuDao;
import com.xw.service.RoleMenuService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xw.req.PageRequest;

import java.util.List;

import jakarta.annotation.Resource;

/**
 * (RoleMenu)表服务实现类
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService {
    @Resource
    private RoleMenuDao roleMenuDao;

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @Override
    public PageInfo<RoleMenu> queryByPage(PageRequest<RoleMenu> pageRequest) {
        //开始分页
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<RoleMenu> list = this.roleMenuDao.queryAll(pageRequest.getQ());
        PageInfo<RoleMenu> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
