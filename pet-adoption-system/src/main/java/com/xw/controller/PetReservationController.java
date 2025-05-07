package com.xw.controller;

import com.xw.common.Constants;
import com.xw.common.Result;
import com.xw.dao.PetReservationDao;
import com.xw.entity.PetReservation;
import com.xw.req.PageRequest;
import com.xw.service.PetReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("petReservation")
@Tag(name = "宠物预约管理")
public class PetReservationController {
    @Resource
    private PetReservationService petReservationService;

    @Resource
    private PetReservationDao petReservationDao;

    @PostMapping("page")
    @Operation(summary = "分页查询")
    public Result<?> findPage(@RequestBody PageRequest<PetReservation> pageRequest) {
        return new Result<>(petReservationService.queryByPage(pageRequest), Constants.SUCCESS);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询单条数据")
    public Result<?> findOne(@PathVariable("id") Long id) {
        PetReservation petReservation = petReservationDao.queryById(id);
        return new Result<>(petReservation,Constants.SUCCESS);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除数据")
    public Result<?> delete(@PathVariable("id") Long id) {
        petReservationDao.deleteById(id);
        return new Result<>(Constants.SUCCESS);
    }

    @PostMapping("deleteBatchByIds")
    @Operation(summary = "批量删除")
    public Result<?> deleteBatchByIds(@RequestBody List<Long> ids) {
        petReservationDao.deleteBatchByIds(ids);
        return new Result<>(Constants.SUCCESS);
    }
}
