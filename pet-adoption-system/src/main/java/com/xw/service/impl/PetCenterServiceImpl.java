package com.xw.service.impl;
import com.xw.dao.PetStarDao;
import com.xw.entity.PetCenter;
import com.xw.dao.PetCenterDao;
import com.xw.entity.PetStar;
import com.xw.service.PetCenterService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xw.req.PageRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import jakarta.annotation.Resource;
/**
 * 宠物中心
 */
@Service("petCenterService")
public class PetCenterServiceImpl implements PetCenterService {
    @Resource
    private PetCenterDao petCenterDao;
    @Resource
    private PetStarDao petStarDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @Override
    public PageInfo<PetCenter> queryByPage(PageRequest<PetCenter> pageRequest) {
        //开始分页
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<PetCenter> list = this.petCenterDao.queryAll(pageRequest.getQ());
        List<Integer> ids = list.stream().map(PetCenter::getId).collect(Collectors.toList());
        List<PetStar> petStars = new ArrayList<>();
        if (!CollectionUtils.isEmpty(ids)) {
            PetStar petStarQuery = new PetStar();
            petStarQuery.setPetCenterIds(ids);
            petStars = petStarDao.queryAll(petStarQuery);
        }
        for (PetCenter petCenter : list) {
            if (StringUtils.hasText(petCenter.getImageUrlList())) {
                petCenter.setMenuImages(Arrays.stream(petCenter.getImageUrlList().split(",")).collect(Collectors.toList()));
            }
            petCenter.setStarNum(0);
            Optional<PetStar> optionalPetStar = petStars.stream().filter(u -> u.getPetCenterId().compareTo(petCenter.getId()) == 0).findAny();
            if (optionalPetStar.isPresent()) {
                petCenter.setStarNum(optionalPetStar.get().getStarNum());
            }
        }
        PageInfo<PetCenter> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}