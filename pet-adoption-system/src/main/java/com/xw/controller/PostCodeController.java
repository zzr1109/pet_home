package com.xw.controller;
import com.xw.dao.CommentDao;
import com.xw.entity.PostCode;
import com.xw.service.PostCodeService;
import com.xw.dao.PostCodeDao;
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
 * 宠物圈帖子管理
 */
@RestController
@RequestMapping("postCode")
@Tag(name = "宠物圈帖子管理")
public class PostCodeController {
    /**
     * 服务对象
     */
    @Resource
    private PostCodeService postCodeService;
    @Resource
    private PostCodeDao postCodeDao;
    @Resource
    private CommentDao commentDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询")
    public Result<?> findPage(@RequestBody PageRequest<PostCode> pageRequest) {
        return new Result<>(this.postCodeService.queryByPage(pageRequest),Constants.SUCCESS);
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
        PostCode postCode =  this.postCodeDao.queryById(id);
        if (StringUtils.hasText(postCode.getImageUrlList())) {
            postCode.setMenuImages(Arrays.stream(postCode.getImageUrlList().split(",")).collect(Collectors.toList()));
        }
        return new Result<>(postCode,Constants.SUCCESS);
    }
    /**
     * 新增/修改数据
     *
     * @param postCode 实体
     * @return 新增/修改结果
     */
    @PostMapping
    @Operation(summary = "新增/修改数据")
    public Result<?> saveOrUpdate(@RequestBody @Validated PostCode postCode) {
        if (!CollectionUtils.isEmpty(postCode.getMenuImages())) {
            postCode.setImageUrlList(postCode.getMenuImages().stream().collect(Collectors.joining(",")));
        }
        if (postCode.getId() == null) {
            postCode.setUserInfo(TokenHandler.getUserInfo());
            this.postCodeDao.insert(postCode);
        } else {
            this.postCodeDao.updateEntity(postCode);
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
        this.postCodeDao.deleteById(id);
        commentDao.deleteByTypeAndTypeId(0,id);
        return new Result<>(Constants.SUCCESS);
    }
    @PostMapping("deleteBatchByIds")
    @Operation(summary = "批量删除")
    public Result<?> deleteBatchByIds(@RequestBody List<Integer> ids) {
        this.postCodeDao.deleteBatchByIds(ids);
        for (Integer id : ids) {
            commentDao.deleteByTypeAndTypeId(0,id);
        }
        return new Result<>(Constants.SUCCESS);
    }
}