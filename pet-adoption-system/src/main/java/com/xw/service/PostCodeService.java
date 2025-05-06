package com.xw.service;

import com.github.pagehelper.PageInfo;
import com.xw.entity.Comment;
import com.xw.entity.PostCode;
import com.xw.req.PageRequest;

public interface PostCodeService {


    PageInfo<PostCode> queryByPage(PageRequest<PostCode> pageRequest);
}
