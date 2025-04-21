package com.xw.controller;

import com.xw.common.Constants;
import com.xw.common.Result;
import com.xw.common.TokenHandler;
import com.xw.dao.PetCenterDao;
import com.xw.entity.PetCenter;
import com.xw.req.PageRequest;
import com.xw.service.PetCenterService;
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
@RequestMapping("petCenter")
@Tag(name = "宠物中心")
public class PetCenterController {
    @Resource
    private PetCenterService petCenterService;

    @Resource
    private PetCenterDao petCenterDao;

    @PostMapping("page")
    @Operation(summary = "分页查询")
    public Result<?> findPage(@RequestBody PageRequest<PetCenter> pageRequest) {
        return new Result<>(petCenterService.queryByPage(pageRequest), Constants.SUCCESS);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询单条数据")
    public Result<?> findOne(@PathVariable("id") Integer id) {
        PetCenter petCenter = petCenterDao.queryById(id);
        if (StringUtils.hasText(petCenter.getImageUrlList())) {
            petCenter.setMenuImages(Arrays.stream(petCenter.getImageUrlList().split(",")).collect(Collectors.toList()));
        }
        return new Result<>(petCenter,Constants.SUCCESS);
    }

    @PostMapping
    @Operation(summary = "新增/修改数据")
    public Result<?> saveOrUpdate(@RequestBody @Validated PetCenter petCenter) {
        if (!CollectionUtils.isEmpty(petCenter.getMenuImages())) {
            petCenter.setImageUrlList(petCenter.getMenuImages().stream().collect(Collectors.joining(",")));
        }
        if (petCenter.getId() == null) {
            petCenter.setUserInfo(TokenHandler.getUserInfo());
            petCenterDao.insert(petCenter);
        } else {
            petCenterDao.updateEntity(petCenter);
        }
        return new Result<>(Constants.SUCCESS);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除数据")
    public Result<?> delete(@PathVariable("id") Integer id) {
        petCenterDao.deleteById(id);
        return new Result<>(Constants.SUCCESS);
    }

    @PostMapping("deleteBatchByIds")
    @Operation(summary = "批量删除")
    public Result<?> deleteBatchByIds(@RequestBody List<Integer> ids) {
        petCenterDao.deleteBatchByIds(ids);
        return new Result<>(Constants.SUCCESS);
    }
}
