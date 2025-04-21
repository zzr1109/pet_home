package com.xw.service;

import com.github.pagehelper.PageInfo;
import com.xw.entity.PetCenter;
import com.xw.entity.PetCrousel;
import com.xw.req.PageRequest;

public interface PetCenterService {


    PageInfo<PetCenter> queryByPage(PageRequest<PetCenter> pageRequest);
}
