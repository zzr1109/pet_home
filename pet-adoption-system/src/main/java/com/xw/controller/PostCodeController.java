package com.xw.controller;

import com.xw.common.Constants;
import com.xw.common.Result;
import com.xw.common.TokenHandler;
import com.xw.dao.PostCodeDao;
import com.xw.entity.PostCode;
import com.xw.req.PageRequest;
import com.xw.service.PostCodeService;
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
@RequestMapping("postCode")
@Tag(name = "帖子管理")
public class PostCodeController {
    @Resource
    private PostCodeService postCodeService;

    @Resource
    private PostCodeDao postCodeDao;

    @PostMapping("page")
    @Operation(summary = "分页查询")
    public Result<?> findPage(@RequestBody PageRequest<PostCode> pageRequest) {
        return new Result<>(postCodeService.queryByPage(pageRequest), Constants.SUCCESS);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询单条数据")
    public Result<?> findOne(@PathVariable("id") Integer id) {
        PostCode postCode = postCodeDao.queryById(id);
        if (StringUtils.hasText(postCode.getImageUrlList())) {
            postCode.setMenuImages(Arrays.stream(postCode.getImageUrlList().split(",")).collect(Collectors.toList()));
        }
        return new Result<>(postCode,Constants.SUCCESS);
    }

    @PostMapping
    @Operation(summary = "新增/修改数据")
    public Result<?> saveOrUpdate(@RequestBody @Validated PostCode postCode) {
        if (!CollectionUtils.isEmpty(postCode.getMenuImages())) {
            postCode.setImageUrlList(postCode.getMenuImages().stream().collect(Collectors.joining(",")));
        }
        if (postCode.getId() == null) {
            postCode.setUserInfo(TokenHandler.getUserInfo());
            postCodeDao.insert(postCode);
        } else {
            postCodeDao.updateEntity(postCode);
        }
        return new Result<>(Constants.SUCCESS);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除数据")
    public Result<?> delete(@PathVariable("id") Integer id) {
        postCodeDao.deleteById(id);
        return new Result<>(Constants.SUCCESS);
    }

    @PostMapping("deleteBatchByIds")
    @Operation(summary = "批量删除")
    public Result<?> deleteBatchByIds(@RequestBody List<Integer> ids) {
        postCodeDao.deleteBatchByIds(ids);
        return new Result<>(Constants.SUCCESS);
    }
}
