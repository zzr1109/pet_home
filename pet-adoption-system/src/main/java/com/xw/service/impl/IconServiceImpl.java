package com.xw.service.impl;

import com.xw.entity.Icon;
import com.xw.dao.IconDao;
import com.xw.service.IconService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xw.req.PageRequest;

import java.util.List;



/**
 * (Icon)表服务实现类
 */
@Service("iconService")
public class IconServiceImpl implements IconService {
    @Resource
    private IconDao iconDao;

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @Override
    public PageInfo<Icon> queryByPage(PageRequest<Icon> pageRequest) {
        //开始分页
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<Icon> list = this.iconDao.queryAll(pageRequest.getQ());
        PageInfo<Icon> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
