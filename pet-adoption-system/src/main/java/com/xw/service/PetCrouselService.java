package com.xw.service;

import com.github.pagehelper.PageInfo;
import com.xw.entity.PetCrousel;
import com.xw.req.PageRequest;

public interface PetCrouselService {
    PageInfo<PetCrousel> queryByPage(PageRequest<PetCrousel> pageRequest);

}
