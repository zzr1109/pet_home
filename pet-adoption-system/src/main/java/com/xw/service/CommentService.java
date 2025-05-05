package com.xw.service;

import com.github.pagehelper.PageInfo;
import com.xw.entity.Comment;
import com.xw.entity.PetCenter;
import com.xw.req.PageRequest;

public interface CommentService {


    PageInfo<Comment> queryByPage(PageRequest<Comment> pageRequest);
}
