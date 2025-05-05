package com.xw.controller;

import com.xw.common.Constants;
import com.xw.common.Result;
import com.xw.common.TokenHandler;
import com.xw.dao.CommentDao;
import com.xw.entity.Comment;
import com.xw.req.PageRequest;
import com.xw.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
@Tag(name = "评论管理")
public class CommentController {
    @Resource
    private CommentService commentService;

    @Resource
    private CommentDao commentDao;

    @PostMapping("page")
    @Operation(summary = "分页查询")
    public Result<?> findPage(@RequestBody PageRequest<Comment> pageRequest) {
        return new Result<>(commentService.queryByPage(pageRequest), Constants.SUCCESS);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询单条数据")
    public Result<?> findOne(@PathVariable("id") Long id) {
        Comment comment = commentDao.queryById(id);
        return new Result<>(comment,Constants.SUCCESS);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除数据")
    public Result<?> delete(@PathVariable("id") Long id) {
        commentDao.deleteById(id);
        return new Result<>(Constants.SUCCESS);
    }

    @PostMapping("deleteBatchByIds")
    @Operation(summary = "批量删除")
    public Result<?> deleteBatchByIds(@RequestBody List<Long> ids) {
        commentDao.deleteBatchByIds(ids);
        return new Result<>(Constants.SUCCESS);
    }
}
