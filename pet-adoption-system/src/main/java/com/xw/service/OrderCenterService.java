package com.xw.service;
import com.xw.entity.OrderCenter;
import com.xw.req.PageRequest;
import com.github.pagehelper.PageInfo;
/**
 * 订单管理中心
 */
public interface OrderCenterService {
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    PageInfo<OrderCenter> queryByPage(PageRequest<OrderCenter> pageRequest);

    void opOrder(OrderCenter orderCenter);
}