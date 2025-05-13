package com.xw.service.impl;
import com.xw.dao.GoodsCenterDao;
import com.xw.entity.GoodsCenter;
import com.xw.entity.OrderCarCenter;
import com.xw.entity.OrderDetailCenter;
import com.xw.dao.OrderDetailCenterDao;
import com.xw.service.OrderDetailCenterService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xw.req.PageRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import jakarta.annotation.Resource;
/**
 * 订单详情
 */
@Service("orderDetailCenterService")
public class OrderDetailCenterServiceImpl implements OrderDetailCenterService {
    @Resource
    private OrderDetailCenterDao orderDetailCenterDao;

    @Resource
    private GoodsCenterDao goodsCenterDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @Override
    public PageInfo<OrderDetailCenter> queryByPage(PageRequest<OrderDetailCenter> pageRequest) {
        //开始分页
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<OrderDetailCenter> list = this.orderDetailCenterDao.queryAll(pageRequest.getQ());
        PageInfo<OrderDetailCenter> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<OrderDetailCenter> queryDetail(String orderNumber) {
        OrderDetailCenter orderDetailCenter = new OrderDetailCenter();
        orderDetailCenter.setOrderNumber(orderNumber);
        List<OrderDetailCenter> orderDetailCenters = orderDetailCenterDao.queryAll(orderDetailCenter);
        List<Integer> goodsIds = orderDetailCenters.stream().map(OrderDetailCenter::getGoodsCenterId).distinct().collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(goodsIds)) {
            GoodsCenter goodsCenter = new GoodsCenter();
            goodsCenter.setIds(goodsIds);
            List<GoodsCenter> goodsCenters = goodsCenterDao.queryAll(goodsCenter);
            for (GoodsCenter center : goodsCenters) {
                if (StringUtils.hasText(center.getImageUrlList())) {
                    center.setMenuImages(Arrays.stream(center.getImageUrlList().split(",")).collect(Collectors.toList()));
                }
            }
            for (OrderDetailCenter orderDetailCenterData : orderDetailCenters) {
                Optional<GoodsCenter> goodsCenterOptional = goodsCenters.stream().filter(u -> u.getId().compareTo(orderDetailCenterData.getGoodsCenterId()) == 0).findAny();
                if (!goodsCenterOptional.isPresent()) {
                    continue;
                }
                orderDetailCenterData.setGoodsCenter(goodsCenterOptional.get());
            }
        }
        return orderDetailCenters;
    }
}