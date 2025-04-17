package com.xw.service;

import com.xw.entity.Icon;
import com.xw.req.PageRequest;
import com.github.pagehelper.PageInfo;

/**
 * (Icon)表服务接口
 */
public interface IconService {

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    PageInfo<Icon> queryByPage(PageRequest<Icon> pageRequest);

}
