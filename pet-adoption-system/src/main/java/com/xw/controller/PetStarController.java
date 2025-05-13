package com.xw.controller;
import com.xw.entity.PetStar;
import com.xw.service.PetStarService;
import com.xw.dao.PetStarDao;
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
 * 宠物明星值管理
 */
@RestController
@RequestMapping("petStar")
@Tag(name = "宠物明星值管理")
public class PetStarController {
    /**
     * 服务对象
     */
    @Resource
    private PetStarService petStarService;
    @Resource
    private PetStarDao petStarDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询")
    public Result<?> findPage(@RequestBody PageRequest<PetStar> pageRequest) {
        return new Result<>(this.petStarService.queryByPage(pageRequest),Constants.SUCCESS);
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
        PetStar petStar =  this.petStarDao.queryById(id);
        return new Result<>(petStar,Constants.SUCCESS);
    }
    /**
     * 新增/修改数据
     *
     * @param petStar 实体
     * @return 新增/修改结果
     */
    @PostMapping
    @Operation(summary = "新增/修改数据")
    public Result<?> saveOrUpdate(@RequestBody @Validated PetStar petStar) {
        if (petStar.getId() == null) {
            petStar.setUserInfo(TokenHandler.getUserInfo());
            this.petStarDao.insert(petStar);
        } else {
            this.petStarDao.updateEntity(petStar);
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
        this.petStarDao.deleteById(id);
        return new Result<>(Constants.SUCCESS);
    }
    @PostMapping("deleteBatchByIds")
    @Operation(summary = "批量删除")
    public Result<?> deleteBatchByIds(@RequestBody List<Integer> ids) {
        this.petStarDao.deleteBatchByIds(ids);
        return new Result<>(Constants.SUCCESS);
    }
}