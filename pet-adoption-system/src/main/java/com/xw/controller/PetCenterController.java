package com.xw.controller;
import com.xw.entity.PetCenter;
import com.xw.service.PetCenterService;
import com.xw.dao.PetCenterDao;
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
 * 宠物中心
 */
@RestController
@RequestMapping("petCenter")
@Tag(name = "宠物中心")
public class PetCenterController {
    /**
     * 服务对象
     */
    @Resource
    private PetCenterService petCenterService;
    @Resource
    private PetCenterDao petCenterDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询")
    public Result<?> findPage(@RequestBody PageRequest<PetCenter> pageRequest) {
        return new Result<>(this.petCenterService.queryByPage(pageRequest),Constants.SUCCESS);
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
        PetCenter petCenter =  this.petCenterDao.queryById(id);
        if (StringUtils.hasText(petCenter.getImageUrlList())) {
            petCenter.setMenuImages(Arrays.stream(petCenter.getImageUrlList().split(",")).collect(Collectors.toList()));
        }
        return new Result<>(petCenter,Constants.SUCCESS);
    }
    /**
     * 新增/修改数据
     *
     * @param petCenter 实体
     * @return 新增/修改结果
     */
    @PostMapping
    @Operation(summary = "新增/修改数据")
    public Result<?> saveOrUpdate(@RequestBody @Validated PetCenter petCenter) {
        if (!CollectionUtils.isEmpty(petCenter.getMenuImages())) {
            petCenter.setImageUrlList(petCenter.getMenuImages().stream().collect(Collectors.joining(",")));
        }
        if (petCenter.getId() == null) {
            petCenter.setUserInfo(TokenHandler.getUserInfo());
            this.petCenterDao.insert(petCenter);
        } else {
            this.petCenterDao.updateEntity(petCenter);
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
        this.petCenterDao.deleteById(id);
        return new Result<>(Constants.SUCCESS);
    }
    @PostMapping("deleteBatchByIds")
    @Operation(summary = "批量删除")
    public Result<?> deleteBatchByIds(@RequestBody List<Integer> ids) {
        this.petCenterDao.deleteBatchByIds(ids);
        return new Result<>(Constants.SUCCESS);
    }
}