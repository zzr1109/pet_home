package com.xw.service;

import com.github.pagehelper.PageInfo;
import com.xw.entity.Comment;
import com.xw.entity.PetReservation;
import com.xw.req.PageRequest;

public interface PetReservationService {


    PageInfo<PetReservation> queryByPage(PageRequest<PetReservation> pageRequest);
}
