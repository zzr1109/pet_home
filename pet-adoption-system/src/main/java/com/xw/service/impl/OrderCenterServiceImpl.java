package com.xw.service.impl;
import com.xw.common.Constants;
import com.xw.dao.GoodsCenterDao;
import com.xw.dao.OrderDetailCenterDao;
import com.xw.entity.GoodsCenter;
import com.xw.entity.OrderCenter;
import com.xw.dao.OrderCenterDao;
import com.xw.entity.OrderDetailCenter;
import com.xw.exp.ServiceException;
import com.xw.service.OrderCenterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xw.req.PageRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import jakarta.annotation.Resource;
/**
 * 订单管理中心
 */
@Service("orderCenterService")
@Slf4j
public class OrderCenterServiceImpl implements OrderCenterService {
    @Resource
    private OrderCenterDao orderCenterDao;
    @Resource
    private GoodsCenterDao goodsCenterDao;
    @Resource
    private OrderDetailCenterDao orderDetailCenterDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @Override
    public PageInfo<OrderCenter> queryByPage(PageRequest<OrderCenter> pageRequest) {
        //开始分页
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<OrderCenter> list = this.orderCenterDao.queryAll(pageRequest.getQ());
        PageInfo<OrderCenter> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = { Exception.class, ServiceException.class })
    public void opOrder(OrderCenter orderCenter) {
        try {
            if (orderCenter.getId() == null) {
                throw new ServiceException(Constants.ERROR.getCode(), "请选择订单！");
            }
            OrderCenter orderCenterQuery = orderCenterDao.queryById(orderCenter.getId());
            if (Objects.isNull(orderCenterQuery)) {
                throw new ServiceException(Constants.ERROR.getCode(), "不存在的订单！");
            }
            if (orderCenter.getStatus() == null) {
                throw new ServiceException(Constants.ERROR.getCode(), "修改内容不能为空！");
            }
            OrderCenter orderCenterUpdate = new OrderCenter();
            orderCenterUpdate.setId(orderCenter.getId());
            orderCenterUpdate.setStatus(orderCenter.getStatus());
            orderCenterDao.updateById(orderCenterUpdate);
            // 取消订单,归还库存
            if (orderCenter.getStatus().compareTo(1) == 0) {
                OrderDetailCenter orderDetailCenter = new OrderDetailCenter();
                orderDetailCenter.setOrderCenterId(orderCenter.getId());
                List<OrderDetailCenter> orderDetailCenters = orderDetailCenterDao.queryAll(orderDetailCenter);
                List<Integer> goodsIds = orderDetailCenters.stream().map(OrderDetailCenter::getGoodsCenterId).distinct().collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(goodsIds)) {
                    GoodsCenter goodsCenter = new GoodsCenter();
                    goodsCenter.setIds(goodsIds);
                    List<GoodsCenter> goodsCenters = goodsCenterDao.queryAll(goodsCenter);
                    if (!CollectionUtils.isEmpty(goodsCenters)) {
                        for (GoodsCenter center : goodsCenters) {
                            List<OrderDetailCenter> findOrderDetailList = orderDetailCenters.stream().filter(u -> u.getGoodsCenterId().compareTo(center.getId()) == 0).collect(Collectors.toList());
                            Optional<Integer> reduce = findOrderDetailList.stream().map(OrderDetailCenter::getNumber).reduce((m, n) -> m + n);
                            GoodsCenter update = new GoodsCenter();
                            update.setBuyNumber(center.getBuyNumber() - reduce.orElse(0));
                            update.setId(center.getId());
                            goodsCenterDao.updateById(update);
                        }
                    }
                }
            }
        } catch (ServiceException e) {
            throw new ServiceException(Constants.ERROR.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("系统错误: {}",e.getMessage());
            throw new ServiceException(Constants.ERROR.getCode(), "系统开小差了,请稍后再试！！！");
        }
    }
}