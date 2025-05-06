package com.xw.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xw.dao.PetCrouselDao;
import com.xw.dao.PostCodeDao;
import com.xw.entity.PetCrousel;
import com.xw.entity.PostCode;
import com.xw.req.PageRequest;
import com.xw.service.PetCrouselService;
import com.xw.service.PostCodeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostCodeServiceImpl implements PostCodeService {
    @Resource
    private PostCodeDao postCodeDao;

    @Override
    public PageInfo<PostCode> queryByPage(PageRequest<PostCode> pageRequest) {
        // 开始分页
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<PostCode> list = postCodeDao.queryAll(pageRequest.getQ());
        // 处理图片
        for (PostCode postCode : list) {
            if (StringUtils.hasText(postCode.getImageUrlList())) {
                postCode.setMenuImages(Arrays.stream(postCode.getImageUrlList().split(",")).collect(Collectors.toList()));
            }
        }
        PageInfo<PostCode> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
