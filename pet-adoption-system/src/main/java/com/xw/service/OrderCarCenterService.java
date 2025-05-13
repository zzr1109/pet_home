package com.xw.service;
import com.xw.entity.OrderCarCenter;
import com.xw.req.PageRequest;
import com.github.pagehelper.PageInfo;
/**
 * 购物车模块
 */
public interface OrderCarCenterService {
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    PageInfo<OrderCarCenter> queryByPage(PageRequest<OrderCarCenter> pageRequest);
}