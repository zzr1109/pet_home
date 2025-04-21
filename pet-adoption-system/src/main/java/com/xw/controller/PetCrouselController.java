package com.xw.controller;

import com.xw.common.Constants;
import com.xw.common.Result;
import com.xw.common.TokenHandler;
import com.xw.dao.PetCrouselDao;
import com.xw.entity.PetCrousel;
import com.xw.req.PageRequest;
import com.xw.service.PetCrouselService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("petCrousel")
@Tag(name = "宠物轮播图")
public class PetCrouselController {
    @Resource
    private PetCrouselService petCrouselService;

    @Resource
    private PetCrouselDao petCrouselDao;

    @PostMapping("page")
    @Operation(summary = "分页查询")
    public Result<?> findPage(@RequestBody PageRequest<PetCrousel> pageRequest) {
        return new Result<>(petCrouselService.queryByPage(pageRequest), Constants.SUCCESS);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询单条数据")
    public Result<?> findOne(@PathVariable("id") Integer id) {
        PetCrousel petCrousel = petCrouselDao.queryById(id);
        if (StringUtils.hasText(petCrousel.getImageUrlList())) {
            petCrousel.setMenuImages(Arrays.stream(petCrousel.getImageUrlList().split(",")).collect(Collectors.toList()));
        }
        return new Result<>(petCrousel,Constants.SUCCESS);
    }

    @PostMapping
    @Operation(summary = "新增/修改数据")
    public Result<?> saveOrUpdate(@RequestBody @Validated PetCrousel petCrousel) {
        if (!CollectionUtils.isEmpty(petCrousel.getMenuImages())) {
            petCrousel.setImageUrlList(petCrousel.getMenuImages().stream().collect(Collectors.joining(",")));
        }
        if (petCrousel.getId() == null) {
            petCrousel.setUserInfo(TokenHandler.getUserInfo());
            petCrouselDao.insert(petCrousel);
        } else {
            petCrouselDao.updateEntity(petCrousel);
        }
        return new Result<>(Constants.SUCCESS);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除数据")
    public Result<?> delete(@PathVariable("id") Integer id) {
        petCrouselDao.deleteById(id);
        return new Result<>(Constants.SUCCESS);
    }

    @PostMapping("deleteBatchByIds")
    @Operation(summary = "批量删除")
    public Result<?> deleteBatchByIds(@RequestBody List<Integer> ids) {
        petCrouselDao.deleteBatchByIds(ids);
        return new Result<>(Constants.SUCCESS);
    }
}
