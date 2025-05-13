package com.xw.controller.plat;


import com.xw.common.Constants;
import com.xw.common.Result;
import com.xw.common.TokenHandler;
import com.xw.dao.CommentDao;
import com.xw.dao.PostCodeDao;
import com.xw.entity.Comment;
import com.xw.entity.PostCode;
import com.xw.exp.ServiceException;
import com.xw.req.PageRequest;
import com.xw.service.PostCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/plat/post")
@Tag(name = "（用户端）帖子模块")
@Slf4j
public class PlatPostController {
    @Resource
    private PostCodeDao postCodeDao;

    @Resource
    private PostCodeService postCodeService;

    @PostMapping("posting")
    @Operation(summary = "发帖")
    public Result<?> posting(@RequestBody @Validated PostCode postCode) {
        if (!CollectionUtils.isEmpty(postCode.getMenuImages())) {
            postCode.setImageUrlList(postCode.getMenuImages().stream().collect(Collectors.joining(",")));
        }
        postCode.setUserInfo(TokenHandler.getUserInfo());
        this.postCodeDao.insert(postCode);
        return new Result<>(Constants.SUCCESS);
    }

    @PostMapping("postPage")
    @Operation(summary = "帖子列表")
    public Result<?> postPage(@RequestBody PageRequest<PostCode> pageRequest) {
        return new Result<>(this.postCodeService.queryByPage(pageRequest),Constants.SUCCESS);
    }
}
