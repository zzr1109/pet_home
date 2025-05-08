package com.xw.controller;

import com.xw.common.Constants;
import com.xw.common.Result;
import com.xw.dao.PetAdoptionCenterDao;
import com.xw.entity.PetAdoptionCenter;
import com.xw.req.PageRequest;
import com.xw.service.PetAdoptionCenterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("petAdoptionCenter")
@Tag(name = "宠物领养管理")
public class PetAdoptionCenterController {
    @Resource
    private PetAdoptionCenterService petAdoptionCenterService;

    @Resource
    private PetAdoptionCenterDao petAdoptionCenterDao;

    @PostMapping("page")
    @Operation(summary = "分页查询")
    public Result<?> findPage(@RequestBody PageRequest<PetAdoptionCenter> pageRequest) {
        return new Result<>(petAdoptionCenterService.queryByPage(pageRequest), Constants.SUCCESS);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询单条数据")
    public Result<?> findOne(@PathVariable("id") Long id) {
        PetAdoptionCenter petAdoptionCenter = petAdoptionCenterDao.queryById(id);
        return new Result<>(petAdoptionCenter,Constants.SUCCESS);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除数据")
    public Result<?> delete(@PathVariable("id") Long id) {
        petAdoptionCenterDao.deleteById(id);
        return new Result<>(Constants.SUCCESS);
    }

    @PostMapping("deleteBatchByIds")
    @Operation(summary = "批量删除")
    public Result<?> deleteBatchByIds(@RequestBody List<Long> ids) {
        petAdoptionCenterDao.deleteBatchByIds(ids);
        return new Result<>(Constants.SUCCESS);
    }
}
