package com.xw.controller.plat;


import cn.hutool.core.util.IdUtil;
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

@RestController
@RequestMapping("/plat/comment")
@Tag(name = "（用户端）评论模块")
public class PlatCommentController {
    @Resource
    private CommentDao commentDao;

    @Resource
    private CommentService commentService;

    @PostMapping("postComments")
    @Operation(summary = "发布评论")
    public Result<?> postComments(@RequestBody @Validated Comment comment) {
        comment.setUserInfo(TokenHandler.getUserInfo());
        comment.setId(IdUtil.getSnowflakeNextId());
        commentDao.insert(comment);
        return new Result<>(Constants.SUCCESS);
    }

    @PostMapping("recursiveCommentList")
    @Operation(summary = "递归评论列表")
    public Result<?> recursiveCommentList(@RequestBody PageRequest<Comment> pageRequest) {
        return new Result<>(this.commentService.recursivePageList(pageRequest),Constants.SUCCESS);
    }
}
