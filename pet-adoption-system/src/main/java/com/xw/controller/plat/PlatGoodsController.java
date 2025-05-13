package com.xw.controller.plat;


import com.xw.common.Constants;
import com.xw.common.Result;
import com.xw.common.TokenHandler;
import com.xw.dao.OrderCarCenterDao;
import com.xw.entity.GoodsCenter;
import com.xw.entity.OrderCarCenter;
import com.xw.req.PageRequest;
import com.xw.service.GoodsCenterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plat/goods")
@Tag(name = "（用户端）宠物商品模块")
public class PlatGoodsController {
    @Resource
    private GoodsCenterService goodsCenterService;
    @Resource
    private OrderCarCenterDao orderCarCenterDao;

    @PostMapping("page")
    @Operation(summary = "宠物商品列表")
    public Result<?> page(@RequestBody PageRequest<GoodsCenter> pageRequest) {
        return new Result<>(goodsCenterService.queryByPage(pageRequest), Constants.SUCCESS);
    }

    @PostMapping("addCar")
    @Operation(summary = "加入购物车")
    public Result<?> addCar(@RequestBody @Validated OrderCarCenter orderCarCenter) {
        orderCarCenter.setUserInfo(TokenHandler.getUserInfo());
        orderCarCenterDao.insert(orderCarCenter);
        return new Result<>(Constants.SUCCESS);
    }
}
