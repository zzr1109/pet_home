package com.xw.service.impl;
import com.xw.dao.GoodsCenterDao;
import com.xw.entity.GoodsCenter;
import com.xw.entity.OrderCarCenter;
import com.xw.dao.OrderCarCenterDao;
import com.xw.service.OrderCarCenterService;
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
 * 购物车模块
 */
@Service("orderCarCenterService")
public class OrderCarCenterServiceImpl implements OrderCarCenterService {
    @Resource
    private OrderCarCenterDao orderCarCenterDao;
    @Resource
    private GoodsCenterDao goodsCenterDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @Override
    public PageInfo<OrderCarCenter> queryByPage(PageRequest<OrderCarCenter> pageRequest) {
        //开始分页
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<OrderCarCenter> list = this.orderCarCenterDao.queryAll(pageRequest.getQ());
        List<Integer> goodsIds = list.stream().map(OrderCarCenter::getGoodsCenterId).distinct().collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(goodsIds)) {
            GoodsCenter goodsCenter = new GoodsCenter();
            goodsCenter.setIds(goodsIds);
            List<GoodsCenter> goodsCenters = goodsCenterDao.queryAll(goodsCenter);
            for (GoodsCenter center : goodsCenters) {
                if (StringUtils.hasText(center.getImageUrlList())) {
                    center.setMenuImages(Arrays.stream(center.getImageUrlList().split(",")).collect(Collectors.toList()));
                }
            }
            for (OrderCarCenter orderCarCenter : list) {
                Optional<GoodsCenter> goodsCenterOptional = goodsCenters.stream().filter(u -> u.getId().compareTo(orderCarCenter.getGoodsCenterId()) == 0).findAny();
                if (!goodsCenterOptional.isPresent()) {
                    continue;
                }
                orderCarCenter.setGoodsCenter(goodsCenterOptional.get());
            }
        }
        PageInfo<OrderCarCenter> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}