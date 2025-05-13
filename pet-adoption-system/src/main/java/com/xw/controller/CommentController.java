package com.xw.controller;
import com.xw.entity.Comment;
import com.xw.service.CommentService;
import com.xw.dao.CommentDao;
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
 * 评论表
 */
@RestController
@RequestMapping("comment")
@Tag(name = "评论表")
public class CommentController {
    /**
     * 服务对象
     */
    @Resource
    private CommentService commentService;
    @Resource
    private CommentDao commentDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询")
    public Result<?> findPage(@RequestBody PageRequest<Comment> pageRequest) {
        return new Result<>(this.commentService.queryByPage(pageRequest),Constants.SUCCESS);
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    @Operation(summary = "查询单条数据（ID）")
    public Result<?> findOne(@PathVariable("id") Long id) {
        Comment comment =  this.commentDao.queryById(id);
        return new Result<>(comment,Constants.SUCCESS);
    }
    /**
     * 新增/修改数据
     *
     * @param comment 实体
     * @return 新增/修改结果
     */
    @PostMapping
    @Operation(summary = "新增/修改数据")
    public Result<?> saveOrUpdate(@RequestBody @Validated Comment comment) {
        if (comment.getId() == null) {
            comment.setUserInfo(TokenHandler.getUserInfo());
            this.commentDao.insert(comment);
        } else {
            this.commentDao.updateEntity(comment);
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
    public Result<?> delete(@PathVariable("id") Long id) {
        this.commentDao.deleteById(id);
        return new Result<>(Constants.SUCCESS);
    }
    @PostMapping("deleteBatchByIds")
    @Operation(summary = "批量删除")
    public Result<?> deleteBatchByIds(@RequestBody List<Long> ids) {
        this.commentDao.deleteBatchByIds(ids);
        return new Result<>(Constants.SUCCESS);
    }
}