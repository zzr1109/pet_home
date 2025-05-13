package com.xw.controller.plat;


import com.xw.common.Constants;
import com.xw.common.Result;
import com.xw.dao.OrderCenterDao;
import com.xw.entity.OrderCenter;
import com.xw.exp.ServiceException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/plat/pay")
@Tag(name = "（用户端）支付模块")
public class PlatPayController {
    @Resource
    private OrderCenterDao orderCenterDao;

    @PostMapping
    @Operation(summary = "订单支付")
    public Result<?> pay(@RequestBody OrderCenter orderCenter) {
        if (orderCenter.getId() == null) {
            throw new ServiceException(Constants.ERROR.getCode(), "订单ID不能为空!");
        }
        OrderCenter orderCenterQuery = orderCenterDao.queryById(orderCenter.getId());
        if (Objects.isNull(orderCenterQuery)) {
            throw new ServiceException(Constants.ERROR.getCode(), "找不到订单信息！");
        }
        if (orderCenterQuery.getStatus().compareTo(0) == 0 || orderCenterQuery.getStatus().compareTo(2) == 0) {
            throw new ServiceException(Constants.ERROR.getCode(), "订单已支付!");
        }
        OrderCenter orderUpdate = new OrderCenter();
        orderUpdate.setId(orderCenter.getId());
        orderUpdate.setStatus(0);
        orderUpdate.setPayMode(orderCenter.getPayMode());
        orderCenterDao.updateById(orderUpdate);
        return new Result<>(Constants.SUCCESS);
    }
}
