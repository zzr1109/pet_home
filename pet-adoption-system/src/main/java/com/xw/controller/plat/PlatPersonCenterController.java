package com.xw.controller.plat;


import com.xw.common.Constants;
import com.xw.common.Result;
import com.xw.common.TokenHandler;
import com.xw.dao.CommentDao;
import com.xw.dao.PostCodeDao;
import com.xw.entity.*;
import com.xw.exp.ServiceException;
import com.xw.req.PageRequest;
import com.xw.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plat/personCenter")
@Tag(name = "（用户端）个人中心")
@Slf4j
public class PlatPersonCenterController {

    @Resource
    private CommentService commentService;

    @Resource
    private OrderCenterService orderCenterService;

    @Resource
    private PetAdoptionCenterService petAdoptionCenterService;

    @Resource
    private PostCodeService postCodeService;

    @Resource
    private PetReservationService petReservationService;

    @Resource
    private PostCodeDao postCodeDao;

    @Resource
    private CommentDao commentDao;

    @PostMapping("myCommentList")
    @Operation(summary = "我的评论")
    public Result<?> myCommentList(@RequestBody PageRequest<Comment> pageRequest) {
        pageRequest.getQ().setUserId(TokenHandler.getUserInfo().getId());
        return new Result<>(this.commentService.queryByPage(pageRequest), Constants.SUCCESS);
    }

    @PostMapping("page")
    @Operation(summary = "我的订单")
    public Result<?> page(@RequestBody PageRequest<OrderCenter> pageRequest) {
        pageRequest.getQ().setUserId(TokenHandler.getUserInfo().getId());
        return new Result<>(this.orderCenterService.queryByPage(pageRequest),Constants.SUCCESS);
    }

    @PostMapping("adoptPage")
    @Operation(summary = "我的领养")
    public Result<?> adoptPage(@RequestBody PageRequest<PetAdoptionCenter> pageRequest) {
        pageRequest.getQ().setUserId(TokenHandler.getUserInfo().getId());
        return new Result<>(this.petAdoptionCenterService.queryByPage(pageRequest),Constants.SUCCESS);
    }

    @PostMapping("reservationPage")
    @Operation(summary = "我的预约")
    public Result<?> reservationPage(@RequestBody PageRequest<PetReservation> pageRequest) {
        pageRequest.getQ().setUserId(TokenHandler.getUserInfo().getId());
        return new Result<>(this.petReservationService.queryByPage(pageRequest),Constants.SUCCESS);
    }

    @PostMapping("postPage")
    @Operation(summary = "我的分享")
    public Result<?> postPage(@RequestBody PageRequest<PostCode> pageRequest) {
        pageRequest.getQ().setUserId(TokenHandler.getUserInfo().getId());
        return new Result<>(this.postCodeService.queryByPage(pageRequest),Constants.SUCCESS);
    }

    @GetMapping("delPostById")
    @Operation(summary = "删除我的帖子")
    @Transactional(rollbackFor = { ServiceException.class, Exception.class })
    public Result<?> delPostById(@RequestParam("postId") Integer postId) {
        try {
            postCodeDao.deleteById(postId);
            commentDao.deleteByTypeAndTypeId(0,postId);
        } catch (ServiceException e) {
            throw new ServiceException(Constants.ERROR.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("系统错误：{}",e.getMessage());
            throw new ServiceException(Constants.ERROR.getCode(), e.getMessage());
        }
        return new Result<>(Constants.SUCCESS);
    }

    @GetMapping("delMyComment")
    @Operation(summary = "删除我的评论")
    public Result<?> delMyComment(@RequestParam("commentId") Long commentId) {
        commentDao.deleteById(commentId);
        return new Result<>(Constants.SUCCESS);
    }

    @PostMapping("postMyComment")
    @Operation(summary = "我的评论列表")
    public Result<?> postMyComment(@RequestBody PageRequest<Comment> pageRequest) {
        pageRequest.getQ().setUserId(TokenHandler.getUserInfo().getId());
        return new Result<>(this.commentService.queryByPage(pageRequest),Constants.SUCCESS);
    }

    @PostMapping("postMyPage")
    @Operation(summary = "我的发帖列表")
    public Result<?> postMyPage(@RequestBody PageRequest<PostCode> pageRequest) {
        pageRequest.getQ().setUserId(TokenHandler.getUserInfo().getId());
        return new Result<>(this.postCodeService.queryByPage(pageRequest),Constants.SUCCESS);
    }
}
