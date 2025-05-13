package com.xw.service.impl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xw.common.Constants;
import com.xw.entity.Comment;
import com.xw.dao.CommentDao;
import com.xw.exp.ServiceException;
import com.xw.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xw.req.PageRequest;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import jakarta.annotation.Resource;
/**
 * 评论表
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @Override
    public PageInfo<Comment> queryByPage(PageRequest<Comment> pageRequest) {
        //开始分页
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<Comment> list = this.commentDao.queryAll(pageRequest.getQ());
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Map<String,Object> recursivePageList(PageRequest<Comment> pageRequest) {
        if (Objects.isNull(pageRequest.getQ())) {
            throw new ServiceException(Constants.ERROR.getCode(), "参数错误！");
        }
        if (pageRequest.getQ().getType() == null) {
            throw new ServiceException(Constants.ERROR.getCode(), "评论类型不能为空！");
        }
        if (CollectionUtils.isEmpty(pageRequest.getQ().getTypeIds())) {
            return null;
        }
        //开始分页
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<Comment> commentList = this.commentDao.queryAll(pageRequest.getQ());

        List<Long> linkIds = commentList.stream().map(Comment::getLinkId).collect(Collectors.toList());
        Comment commentQuery = new Comment();
        commentQuery.setIds(linkIds);
        commentQuery.setType(pageRequest.getQ().getType());
        commentQuery.setTypeIds(pageRequest.getQ().getTypeIds());
        List<Comment> commentListByLinkIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(linkIds)) {
            commentListByLinkIds = this.commentDao.queryAll(commentQuery);
        }
        List<Comment> comments = new ArrayList<>();
        for (Comment commentData : commentList) {
            Optional<Comment> optionalComment = commentListByLinkIds.stream()
                    .filter(u -> u.getId().compareTo(commentData.getLinkId()) == 0)
                    .findAny();
            optionalComment.ifPresent(comment -> commentData.setNameOfTheEvaluatedPerson(comment.getUserName()));
            comments.add(commentData);
        }
        PageInfo<Comment> commentPageList = new PageInfo<>(commentList);
        PageInfo<Comment> pageOpenInfo = new PageInfo<>(comments);
        pageOpenInfo.setTotal(commentPageList.getTotal());
        pageOpenInfo.setPages(commentPageList.getPages());
        pageOpenInfo.setPageNum(pageOpenInfo.getPageNum());
        pageOpenInfo.setPageSize(pageOpenInfo.getPageSize());
        Map<String,Object> map = new HashMap<>();
        // 展开
        map.put("pageOpenData",pageOpenInfo);
        map.put("pageRequest",pageRequest);
        return map;
    }
}