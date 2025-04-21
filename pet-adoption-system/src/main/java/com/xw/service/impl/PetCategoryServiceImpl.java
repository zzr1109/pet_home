package com.xw.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xw.dao.PetCategoryDao;
import com.xw.dao.PetCrouselDao;
import com.xw.entity.PetCategory;
import com.xw.entity.PetCrousel;
import com.xw.req.PageRequest;
import com.xw.service.PetCategoryService;
import com.xw.service.PetCrouselService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetCategoryServiceImpl implements PetCategoryService {
    @Resource
    private PetCategoryDao petCategoryDao;

    @Override
    public PageInfo<PetCategory> queryByPage(PageRequest<PetCategory> pageRequest) {
        // 开始分页
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<PetCategory> list = petCategoryDao.queryAll(pageRequest.getQ());
        PageInfo<PetCategory> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
