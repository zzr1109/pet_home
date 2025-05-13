package com.xw.controller;
import com.xw.dao.PetAdoptionCenterDao;
import com.xw.entity.PetAdoptionCenter;
import com.xw.entity.PetReservation;
import com.xw.service.PetReservationService;
import com.xw.dao.PetReservationDao;
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
 * 宠物预约管理
 */
@RestController
@RequestMapping("petReservation")
@Tag(name = "宠物预约管理")
public class PetReservationController {
    /**
     * 服务对象
     */
    @Resource
    private PetReservationService petReservationService;
    @Resource
    private PetReservationDao petReservationDao;
    @Resource
    private PetAdoptionCenterDao petAdoptionCenterDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询")
    public Result<?> findPage(@RequestBody PageRequest<PetReservation> pageRequest) {
        return new Result<>(this.petReservationService.queryByPage(pageRequest),Constants.SUCCESS);
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
        PetReservation petReservation =  this.petReservationDao.queryById(id);
        return new Result<>(petReservation,Constants.SUCCESS);
    }
    /**
     * 新增/修改数据
     *
     * @param petReservation 实体
     * @return 新增/修改结果
     */
    @PostMapping
    @Operation(summary = "新增/修改数据")
    public Result<?> saveOrUpdate(@RequestBody @Validated PetReservation petReservation) {
        if (petReservation.getId() == null) {
            petReservation.setUserInfo(TokenHandler.getUserInfo());
            this.petReservationDao.insert(petReservation);
        } else {
            this.petReservationDao.updateEntity(petReservation);
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
        this.petReservationDao.deleteById(id);
        return new Result<>(Constants.SUCCESS);
    }
    @PostMapping("deleteBatchByIds")
    @Operation(summary = "批量删除")
    public Result<?> deleteBatchByIds(@RequestBody List<Integer> ids) {
        this.petReservationDao.deleteBatchByIds(ids);
        return new Result<>(Constants.SUCCESS);
    }

    @PostMapping("opStatus")
    @Operation(summary = "修改状态")
    public Result<?> opStatus(@RequestBody PetReservation petReservation) {
        PetReservation petReservationQuery = petReservationDao.queryById(petReservation.getId());
        PetReservation petReservationUpdate = new PetReservation();
        petReservationUpdate.setStatus(petReservation.getStatus());
        petReservationUpdate.setId(petReservation.getId());
        petReservationDao.updateById(petReservationUpdate);
        if (petReservationUpdate.getStatus().compareTo(2) == 0) {
            PetAdoptionCenter petAdoptionCenter = new PetAdoptionCenter();
            petAdoptionCenter.setUserId(petReservationQuery.getUserId());
            petAdoptionCenter.setUserName(petReservationQuery.getUserName());
            petAdoptionCenter.setUserPhone(petReservationQuery.getUserPhone());
            petAdoptionCenter.setUserImage(petReservationQuery.getUserImage());
            petAdoptionCenter.setUserAddress(petReservationQuery.getUserAddress());
            petAdoptionCenter.setPetCenterId(petReservationQuery.getPetCenterId());
            petAdoptionCenter.setPetCenterName(petReservationQuery.getPetCenterName());
            petAdoptionCenter.setAdoptionStatus(0); // 领养中
            petAdoptionCenterDao.insert(petAdoptionCenter);
        }
        return new Result<>(Constants.SUCCESS);
    }
}