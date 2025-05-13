package com.xw.controller;
import com.xw.dao.PetReservationDao;
import com.xw.entity.PetAdoptionCenter;
import com.xw.entity.PetReservation;
import com.xw.service.PetAdoptionCenterService;
import com.xw.dao.PetAdoptionCenterDao;
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
 * 宠物领养管理
 */
@RestController
@RequestMapping("petAdoptionCenter")
@Tag(name = "宠物领养管理")
public class PetAdoptionCenterController {
    /**
     * 服务对象
     */
    @Resource
    private PetAdoptionCenterService petAdoptionCenterService;
    @Resource
    private PetAdoptionCenterDao petAdoptionCenterDao;
    @Resource
    private PetReservationDao petReservationDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询")
    public Result<?> findPage(@RequestBody PageRequest<PetAdoptionCenter> pageRequest) {
        return new Result<>(this.petAdoptionCenterService.queryByPage(pageRequest),Constants.SUCCESS);
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
        PetAdoptionCenter petAdoptionCenter =  this.petAdoptionCenterDao.queryById(id);
        return new Result<>(petAdoptionCenter,Constants.SUCCESS);
    }
    /**
     * 新增/修改数据
     *
     * @param petAdoptionCenter 实体
     * @return 新增/修改结果
     */
    @PostMapping
    @Operation(summary = "新增/修改数据")
    public Result<?> saveOrUpdate(@RequestBody @Validated PetAdoptionCenter petAdoptionCenter) {
        if (petAdoptionCenter.getId() == null) {
            petAdoptionCenter.setUserInfo(TokenHandler.getUserInfo());
            this.petAdoptionCenterDao.insert(petAdoptionCenter);
        } else {
            this.petAdoptionCenterDao.updateEntity(petAdoptionCenter);
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
        this.petAdoptionCenterDao.deleteById(id);
        return new Result<>(Constants.SUCCESS);
    }
    @PostMapping("deleteBatchByIds")
    @Operation(summary = "批量删除")
    public Result<?> deleteBatchByIds(@RequestBody List<Integer> ids) {
        this.petAdoptionCenterDao.deleteBatchByIds(ids);
        return new Result<>(Constants.SUCCESS);
    }

    @PostMapping("opStatus")
    @Operation(summary = "修改状态")
    public Result<?> opStatus(@RequestBody PetAdoptionCenter petAdoptionCenter) {
        PetAdoptionCenter petAdoptionCenterQuery = petAdoptionCenterDao.queryById(petAdoptionCenter.getId());
        PetAdoptionCenter petAdoptionCenterUpdate = new PetAdoptionCenter();
        petAdoptionCenterUpdate.setAdoptionStatus(petAdoptionCenter.getAdoptionStatus());
        petAdoptionCenterUpdate.setId(petAdoptionCenter.getId());
        petAdoptionCenterDao.updateById(petAdoptionCenterUpdate);
        if (petAdoptionCenter.getAdoptionStatus().compareTo(1) == 0) {
            petReservationDao.updateByUserIdAndPetId(petAdoptionCenterQuery.getUserId(),petAdoptionCenterQuery.getPetCenterId());
        }
        return new Result<>(Constants.SUCCESS);
    }
}