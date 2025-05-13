package com.xw.controller.plat;


import com.xw.common.Constants;
import com.xw.common.Result;
import com.xw.common.TokenHandler;
import com.xw.dao.OrderCarCenterDao;
import com.xw.entity.OrderCarCenter;
import com.xw.req.PageRequest;
import com.xw.service.OrderCarCenterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plat/orderCar")
@Tag(name = "（用户端）购物车模块")
public class PlatOrderCarController {
    @Resource
    private OrderCarCenterService orderCarCenterService;
    @Resource
    private OrderCarCenterDao orderCarCenterDao;

    @PostMapping("/page")
    @Operation(summary = "分页查询")
    public Result<?> findPage(@RequestBody PageRequest<OrderCarCenter> pageRequest) {
        OrderCarCenter orderCarCenter = new OrderCarCenter();
        orderCarCenter.setUserId(TokenHandler.getUserInfo().getId());
        pageRequest.setQ(orderCarCenter);
        return new Result<>(this.orderCarCenterService.queryByPage(pageRequest), Constants.SUCCESS);
    }

    @GetMapping("/delOrderCar")
    @Operation(summary = "购物车删除")
    public Result<?> delOrderCar(@RequestParam("orderCarId") Integer orderCarId) {
        orderCarCenterDao.deleteById(orderCarId);
        return new Result<>(Constants.SUCCESS);
    }
}
