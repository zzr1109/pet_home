package com.xw.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xw.dao.PetCenterDao;
import com.xw.dao.PetCrouselDao;
import com.xw.entity.PetCenter;
import com.xw.entity.PetCrousel;
import com.xw.req.PageRequest;
import com.xw.service.PetCenterService;
import com.xw.service.PetCrouselService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetCenterServiceImpl implements PetCenterService {
    @Resource
    private PetCenterDao petCenterDao;

    @Override
    public PageInfo<PetCenter> queryByPage(PageRequest<PetCenter> pageRequest) {
        // 开始分页
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<PetCenter> list = petCenterDao.queryAll(pageRequest.getQ());
        // 处理图片
        for (PetCenter data : list) {
            if (StringUtils.hasText(data.getImageUrlList())) {
                data.setMenuImages(Arrays.stream(data.getImageUrlList().split(",")).collect(Collectors.toList()));
            }
        }
        PageInfo<PetCenter> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
