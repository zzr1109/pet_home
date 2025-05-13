package com.xw.controller;
import com.xw.entity.PetCrousel;
import com.xw.service.PetCrouselService;
import com.xw.dao.PetCrouselDao;
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
 * 宠物轮播图
 */
@RestController
@RequestMapping("petCrousel")
@Tag(name = "宠物轮播图")
public class PetCrouselController {
    /**
     * 服务对象
     */
    @Resource
    private PetCrouselService petCrouselService;
    @Resource
    private PetCrouselDao petCrouselDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询")
    public Result<?> findPage(@RequestBody PageRequest<PetCrousel> pageRequest) {
        return new Result<>(this.petCrouselService.queryByPage(pageRequest),Constants.SUCCESS);
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
        PetCrousel petCrousel =  this.petCrouselDao.queryById(id);
        if (StringUtils.hasText(petCrousel.getImageUrlList())) {
            petCrousel.setMenuImages(Arrays.stream(petCrousel.getImageUrlList().split(",")).collect(Collectors.toList()));
        }
        return new Result<>(petCrousel,Constants.SUCCESS);
    }
    /**
     * 新增/修改数据
     *
     * @param petCrousel 实体
     * @return 新增/修改结果
     */
    @PostMapping
    @Operation(summary = "新增/修改数据")
    public Result<?> saveOrUpdate(@RequestBody @Validated PetCrousel petCrousel) {
        if (!CollectionUtils.isEmpty(petCrousel.getMenuImages())) {
            petCrousel.setImageUrlList(petCrousel.getMenuImages().stream().collect(Collectors.joining(",")));
        }
        if (petCrousel.getId() == null) {
            petCrousel.setUserInfo(TokenHandler.getUserInfo());
            this.petCrouselDao.insert(petCrousel);
        } else {
            this.petCrouselDao.updateEntity(petCrousel);
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
        this.petCrouselDao.deleteById(id);
        return new Result<>(Constants.SUCCESS);
    }
    @PostMapping("deleteBatchByIds")
    @Operation(summary = "批量删除")
    public Result<?> deleteBatchByIds(@RequestBody List<Integer> ids) {
        this.petCrouselDao.deleteBatchByIds(ids);
        return new Result<>(Constants.SUCCESS);
    }
}