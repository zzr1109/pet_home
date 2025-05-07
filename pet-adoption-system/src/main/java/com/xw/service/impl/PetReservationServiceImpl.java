package com.xw.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xw.dao.CommentDao;
import com.xw.dao.PetReservationDao;
import com.xw.entity.Comment;
import com.xw.entity.PetReservation;
import com.xw.req.PageRequest;
import com.xw.service.CommentService;
import com.xw.service.PetReservationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetReservationServiceImpl implements PetReservationService {
    @Resource
    private PetReservationDao petReservationDao;

    @Override
    public PageInfo<PetReservation> queryByPage(PageRequest<PetReservation> pageRequest) {
        // 开始分页
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<PetReservation> list = petReservationDao.queryAll(pageRequest.getQ());
        PageInfo<PetReservation> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
