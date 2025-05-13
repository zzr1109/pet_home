package com.xw.controller;
import com.xw.entity.OrderDetailCenter;
import com.xw.service.OrderDetailCenterService;
import com.xw.dao.OrderDetailCenterDao;
import org.springframework.web.bind.annotation.*;
import com.xw.common.Constants;
import com.xw.common.Result;
import com.xw.req.PageRequest;
import org.springframework.util.CollectionUtils;
import com.xw.common.TokenHandler;
import java.util.List;
import org.springframework.util.StringUtils;
import java.util.Arrays;
import java.util.stream.Collectors;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
/**
 * 订单详情
 */
@RestController
@RequestMapping("orderDetailCenter")
@Tag(name = "订单详情")
public class OrderDetailCenterController {
    /**
     * 服务对象
     */
    @Resource
    private OrderDetailCenterService orderDetailCenterService;
    @Resource
    private OrderDetailCenterDao orderDetailCenterDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询")
    public Result<?> findPage(@RequestBody PageRequest<OrderDetailCenter> pageRequest) {
        return new Result<>(this.orderDetailCenterService.queryByPage(pageRequest),Constants.SUCCESS);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    @Operation(summary = "查询单条数据（ID）")
    public Result<?> findOne(@PathVariable("id") Integer id) {
        OrderDetailCenter orderDetailCenter =  this.orderDetailCenterDao.queryById(id);
        return new Result<>(orderDetailCenter,Constants.SUCCESS);
    }
    /**
     * 新增/修改数据
     *
     * @param orderDetailCenter 实体
     * @return 新增/修改结果
     */
    @PostMapping
    @Operation(summary = "新增/修改数据")
    public Result<?> saveOrUpdate(@RequestBody @Validated OrderDetailCenter orderDetailCenter) {
        if (orderDetailCenter.getId() == null) {
            orderDetailCenter.setUserInfo(TokenHandler.getUserInfo());
            this.orderDetailCenterDao.insert(orderDetailCenter);
        } else {
            this.orderDetailCenterDao.updateEntity(orderDetailCenter);
        }
        return new Result<>(Constants.SUCCESS);
    }
    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除数据(ID)")
    public Result<?> delete(@PathVariable("id") Integer id) {
        this.orderDetailCenterDao.deleteById(id);
        return new Result<>(Constants.SUCCESS);
    }
    @PostMapping("deleteBatchByIds")
    @Operation(summary = "批量删除")
    public Result<?> deleteBatchByIds(@RequestBody List<Integer> ids) {
        this.orderDetailCenterDao.deleteBatchByIds(ids);
        return new Result<>(Constants.SUCCESS);
    }
}