package com.xw.controller.plat;


import cn.hutool.core.util.IdUtil;
import com.xw.common.Constants;
import com.xw.common.Result;
import com.xw.common.TokenHandler;
import com.xw.dao.GoodsCenterDao;
import com.xw.dao.OrderCarCenterDao;
import com.xw.dao.OrderCenterDao;
import com.xw.dao.OrderDetailCenterDao;
import com.xw.entity.GoodsCenter;
import com.xw.entity.OrderCarCenter;
import com.xw.entity.OrderCenter;
import com.xw.entity.OrderDetailCenter;
import com.xw.exp.ServiceException;
import com.xw.req.PageRequest;
import com.xw.service.OrderCenterService;
import com.xw.service.OrderDetailCenterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/plat/createOrder")
@Tag(name = "（用户端）订单模块")
@Slf4j
public class PlatCreateOrderController {

    @Resource
    private OrderCenterDao orderCenterDao;

    @Resource
    private OrderCenterService orderCenterService;

    @Resource
    private OrderDetailCenterDao orderDetailCenterDao;

    @Resource
    private OrderDetailCenterService orderDetailCenterService;

    @Resource
    private GoodsCenterDao goodsCenterDao;

    @Resource
    private OrderCarCenterDao orderCarCenterDao;

    @PostMapping
    @Operation(summary = "提交订单")
    @Transactional(rollbackFor = {Exception.class,ServiceException.class})
    public Result<?> createOrder(@RequestBody @Validated OrderCenter orderCenter) {
        try {
            if (CollectionUtils.isEmpty(orderCenter.getOrderDetailCenterList())) {
                throw new ServiceException(Constants.ERROR.getCode(), "请选择具体的商品！");
            }
            // 查询商品
            List<Integer> goodIds = orderCenter.getOrderDetailCenterList().stream().map(OrderDetailCenter::getGoodsCenterId).collect(Collectors.toList());
            GoodsCenter goodsCenter = new GoodsCenter();
            goodsCenter.setIds(goodIds);
            List<GoodsCenter> goodsCenterList = goodsCenterDao.queryAll(goodsCenter);
            // 查询购物车
            OrderCarCenter orderCarCenter = new OrderCarCenter();
            orderCarCenter.setGoodsCenterIds(goodIds);
            List<OrderCarCenter> orderCarCenters = orderCarCenterDao.queryAll(orderCarCenter);
            // 计算订单金额
            Optional<BigDecimal> reduce = orderCenter.getOrderDetailCenterList().stream().map(x -> x.getSinglePrice().multiply(new BigDecimal(x.getNumber()))).reduce((m, n) -> m.add(n));
            orderCenter.setUserInfo(TokenHandler.getUserInfo());
            orderCenter.setOrderNumber(IdUtil.getSnowflake().nextIdStr());
            orderCenter.setOrderPrice(reduce.orElse(BigDecimal.ZERO));
            orderCenterDao.insert(orderCenter);
            for (OrderDetailCenter orderDetailCenter : orderCenter.getOrderDetailCenterList()) {
                orderDetailCenter.setOrderCenterId(orderCenter.getId());
                orderDetailCenter.setOrderNumber(orderCenter.getOrderNumber());
                orderDetailCenterDao.insert(orderDetailCenter);
                // 扣减商品库存
                if (CollectionUtils.isEmpty(goodsCenterList)) {
                    continue;
                }
                Optional<GoodsCenter> goodsCenterOptional = goodsCenterList.stream().filter(u -> u.getId().compareTo(orderDetailCenter.getGoodsCenterId()) == 0).findAny();
                if (!goodsCenterOptional.isPresent()) {
                    continue;
                }
                GoodsCenter goodsCenterUpdate = new GoodsCenter();
                goodsCenterUpdate.setId(goodsCenterOptional.get().getId());
                goodsCenterUpdate.setBuyNumber(goodsCenterOptional.get().getBuyNumber() + orderDetailCenter.getNumber());
                if (goodsCenterUpdate.getBuyNumber() > goodsCenterOptional.get().getStockNumber()) {
                    throw new ServiceException(Constants.ERROR.getCode(), "【" + goodsCenterOptional.get().getName() + "】库存不足！");
                }
                goodsCenterDao.updateById(goodsCenterUpdate);
            }
            // 清除购物车
            List<Integer> orderCarIds = orderCarCenters.stream().map(OrderCarCenter::getId).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(orderCarIds)) {
                orderCarCenterDao.deleteBatchByIds(orderCarIds);
            }

        }catch (ServiceException e) {
            throw new ServiceException(Constants.ERROR.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("系统错误:{}",e.getMessage());
            throw new ServiceException(Constants.ERROR.getCode(), "系统开小差了,请稍后再试...");
        }
        return new Result<>(orderCenter,Constants.SUCCESS);
    }


    @GetMapping("seeOrderDetail")
    @Operation(summary = "查看订单详情")
    public Result<?> seeOrderDetail(@RequestParam("orderNumber") String orderNumber) {
        return new Result<>(orderDetailCenterService.queryDetail(orderNumber),Constants.SUCCESS);
    }

    @PostMapping("opOrder")
    @Operation(summary = "订单状态修改")
    public Result<?> opOrder(@RequestBody OrderCenter orderCenter) {
        orderCenterService.opOrder(orderCenter);
        return new Result<>(Constants.SUCCESS);
    }
}
