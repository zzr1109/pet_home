package com.xw.controller;
import com.xw.entity.GoodsCenter;
import com.xw.service.GoodsCenterService;
import com.xw.dao.GoodsCenterDao;
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
 * 商品中心
 */
@RestController
@RequestMapping("goodsCenter")
@Tag(name = "商品中心")
public class GoodsCenterController {
    /**
     * 服务对象
     */
    @Resource
    private GoodsCenterService goodsCenterService;
    @Resource
    private GoodsCenterDao goodsCenterDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询")
    public Result<?> findPage(@RequestBody PageRequest<GoodsCenter> pageRequest) {
        return new Result<>(this.goodsCenterService.queryByPage(pageRequest),Constants.SUCCESS);
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
        GoodsCenter goodsCenter =  this.goodsCenterDao.queryById(id);
        if (StringUtils.hasText(goodsCenter.getImageUrlList())) {
            goodsCenter.setMenuImages(Arrays.stream(goodsCenter.getImageUrlList().split(",")).collect(Collectors.toList()));
        }
        return new Result<>(goodsCenter,Constants.SUCCESS);
    }
    /**
     * 新增/修改数据
     *
     * @param goodsCenter 实体
     * @return 新增/修改结果
     */
    @PostMapping
    @Operation(summary = "新增/修改数据")
    public Result<?> saveOrUpdate(@RequestBody @Validated GoodsCenter goodsCenter) {
        if (!CollectionUtils.isEmpty(goodsCenter.getMenuImages())) {
            goodsCenter.setImageUrlList(goodsCenter.getMenuImages().stream().collect(Collectors.joining(",")));
        }
        if (goodsCenter.getId() == null) {
            goodsCenter.setUserInfo(TokenHandler.getUserInfo());
            this.goodsCenterDao.insert(goodsCenter);
        } else {
            this.goodsCenterDao.updateEntity(goodsCenter);
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
        this.goodsCenterDao.deleteById(id);
        return new Result<>(Constants.SUCCESS);
    }
    @PostMapping("deleteBatchByIds")
    @Operation(summary = "批量删除")
    public Result<?> deleteBatchByIds(@RequestBody List<Integer> ids) {
        this.goodsCenterDao.deleteBatchByIds(ids);
        return new Result<>(Constants.SUCCESS);
    }
}