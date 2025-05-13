package com.xw.controller;
import com.xw.entity.OrderCenter;
import com.xw.service.OrderCenterService;
import com.xw.dao.OrderCenterDao;
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
 * 订单管理中心
 */
@RestController
@RequestMapping("orderCenter")
@Tag(name = "订单管理中心")
public class OrderCenterController {
    /**
     * 服务对象
     */
    @Resource
    private OrderCenterService orderCenterService;
    @Resource
    private OrderCenterDao orderCenterDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询")
    public Result<?> findPage(@RequestBody PageRequest<OrderCenter> pageRequest) {
        return new Result<>(this.orderCenterService.queryByPage(pageRequest),Constants.SUCCESS);
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
        OrderCenter orderCenter =  this.orderCenterDao.queryById(id);
        return new Result<>(orderCenter,Constants.SUCCESS);
    }
    /**
     * 新增/修改数据
     *
     * @param orderCenter 实体
     * @return 新增/修改结果
     */
    @PostMapping
    @Operation(summary = "新增/修改数据")
    public Result<?> saveOrUpdate(@RequestBody @Validated OrderCenter orderCenter) {
        if (orderCenter.getId() == null) {
            orderCenter.setUserInfo(TokenHandler.getUserInfo());
            this.orderCenterDao.insert(orderCenter);
        } else {
            this.orderCenterDao.updateEntity(orderCenter);
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
        this.orderCenterDao.deleteById(id);
        return new Result<>(Constants.SUCCESS);
    }
    @PostMapping("deleteBatchByIds")
    @Operation(summary = "批量删除")
    public Result<?> deleteBatchByIds(@RequestBody List<Integer> ids) {
        this.orderCenterDao.deleteBatchByIds(ids);
        return new Result<>(Constants.SUCCESS);
    }
}