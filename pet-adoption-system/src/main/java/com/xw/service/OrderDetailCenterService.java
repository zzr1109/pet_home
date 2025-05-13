package com.xw.service;
import com.xw.entity.OrderDetailCenter;
import com.xw.req.PageRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 订单详情
 */
public interface OrderDetailCenterService {
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    PageInfo<OrderDetailCenter> queryByPage(PageRequest<OrderDetailCenter> pageRequest);

    List<OrderDetailCenter> queryDetail(String orderNumber);
}