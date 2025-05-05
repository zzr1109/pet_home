package com.xw.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xw.dao.CommentDao;
import com.xw.dao.PetCenterDao;
import com.xw.entity.Comment;
import com.xw.entity.PetCenter;
import com.xw.req.PageRequest;
import com.xw.service.CommentService;
import com.xw.service.PetCenterService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;

    @Override
    public PageInfo<Comment> queryByPage(PageRequest<Comment> pageRequest) {
        // 开始分页
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<Comment> list = commentDao.queryAll(pageRequest.getQ());
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
