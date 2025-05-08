package com.xw.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xw.dao.PetAdoptionCenterDao;
import com.xw.dao.PetReservationDao;
import com.xw.entity.PetAdoptionCenter;
import com.xw.entity.PetReservation;
import com.xw.req.PageRequest;
import com.xw.service.PetAdoptionCenterService;
import com.xw.service.PetReservationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetAdoptionCenterServiceImpl implements PetAdoptionCenterService {
    @Resource
    private PetAdoptionCenterDao petAdoptionCenterDao;

    @Override
    public PageInfo<PetAdoptionCenter> queryByPage(PageRequest<PetAdoptionCenter> pageRequest) {
        // 开始分页
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<PetAdoptionCenter> list = petAdoptionCenterDao.queryAll(pageRequest.getQ());
        PageInfo<PetAdoptionCenter> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
