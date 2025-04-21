package com.xw.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xw.dao.PetCrouselDao;
import com.xw.entity.PetCrousel;
import com.xw.req.PageRequest;
import com.xw.service.PetCrouselService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetCrouselServiceimpl implements PetCrouselService {
    @Resource
    private PetCrouselDao petCrouselDao;
    @Override
    public PageInfo<PetCrousel> queryByPage(PageRequest<PetCrousel> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<PetCrousel> list = petCrouselDao.queryAll(pageRequest.getQ());
        // 处理图片
        for (PetCrousel petCrousel : list) {
            if (StringUtils.hasText(petCrousel.getImageUrlList())) {
                petCrousel.setMenuImages(Arrays.stream(petCrousel.getImageUrlList().split(",")).collect(Collectors.toList()));
            }
        }
        PageInfo<PetCrousel> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}

