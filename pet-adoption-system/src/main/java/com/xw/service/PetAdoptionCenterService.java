package com.xw.service;

import com.github.pagehelper.PageInfo;
import com.xw.entity.PetAdoptionCenter;
import com.xw.entity.PetReservation;
import com.xw.req.PageRequest;

public interface PetAdoptionCenterService {


    PageInfo<PetAdoptionCenter> queryByPage(PageRequest<PetAdoptionCenter> pageRequest);
}
