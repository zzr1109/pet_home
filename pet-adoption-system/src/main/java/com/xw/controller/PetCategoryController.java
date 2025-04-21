package com.xw.controller;

import com.xw.common.Constants;
import com.xw.common.Result;
import com.xw.common.TokenHandler;
import com.xw.dao.PetCategoryDao;
import com.xw.entity.PetCategory;
import com.xw.req.PageRequest;
import com.xw.service.PetCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("petCategory")
@Tag(name = "宠物类别")
public class PetCategoryController {
    @Resource
    private PetCategoryService petCategoryService;

    @Resource
    private PetCategoryDao petCategoryDao;

    @PostMapping("page")
    @Operation(summary = "分页查询")
    public Result<?> findPage(@RequestBody PageRequest<PetCategory> pageRequest) {
        return new Result<>(petCategoryService.queryByPage(pageRequest), Constants.SUCCESS);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询单条数据")
    public Result<?> findOne(@PathVariable("id") Integer id) {
        PetCategory petCategory = petCategoryDao.queryById(id);
        return new Result<>(petCategory,Constants.SUCCESS);
    }

    @PostMapping
    @Operation(summary = "新增/修改数据")
    public Result<?> saveOrUpdate(@RequestBody @Validated PetCategory petCategory) {
        if (petCategory.getId() == null) {
            petCategory.setUserInfo(TokenHandler.getUserInfo());
            petCategoryDao.insert(petCategory);
        } else {
            petCategoryDao.updateEntity(petCategory);
        }
        return new Result<>(Constants.SUCCESS);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除数据")
    public Result<?> delete(@PathVariable("id") Integer id) {
        petCategoryDao.deleteById(id);
        return new Result<>(Constants.SUCCESS);
    }

    @PostMapping("deleteBatchByIds")
    @Operation(summary = "批量删除")
    public Result<?> deleteBatchByIds(@RequestBody List<Integer> ids) {
        petCategoryDao.deleteBatchByIds(ids);
        return new Result<>(Constants.SUCCESS);
    }
}
