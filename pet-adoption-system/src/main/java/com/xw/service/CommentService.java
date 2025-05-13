package com.xw.service;
import com.xw.entity.Comment;
import com.xw.req.PageRequest;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * 评论表
 */
public interface CommentService {
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    PageInfo<Comment> queryByPage(PageRequest<Comment> pageRequest);

    Map<String,Object> recursivePageList(PageRequest<Comment> pageRequest);
}