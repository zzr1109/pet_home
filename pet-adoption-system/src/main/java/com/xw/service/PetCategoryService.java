package com.xw.service;

import com.github.pagehelper.PageInfo;
import com.xw.entity.PetCategory;
import com.xw.entity.PetCrousel;
import com.xw.req.PageRequest;

public interface PetCategoryService {


    PageInfo<PetCategory> queryByPage(PageRequest<PetCategory> pageRequest);
}
