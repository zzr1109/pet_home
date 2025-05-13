package com.xw.service.impl;
import com.xw.entity.PetCrousel;
import com.xw.dao.PetCrouselDao;
import com.xw.service.PetCrouselService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xw.req.PageRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.util.StringUtils;
import jakarta.annotation.Resource;
/**
 * 宠物轮播图
 */
@Service("petCrouselService")
public class PetCrouselServiceImpl implements PetCrouselService {
    @Resource
    private PetCrouselDao petCrouselDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @Override
    public PageInfo<PetCrousel> queryByPage(PageRequest<PetCrousel> pageRequest) {
        //开始分页
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<PetCrousel> list = this.petCrouselDao.queryAll(pageRequest.getQ());
        for (PetCrousel petCrousel : list) {
            if (StringUtils.hasText(petCrousel.getImageUrlList())) {
                petCrousel.setMenuImages(Arrays.stream(petCrousel.getImageUrlList().split(",")).collect(Collectors.toList()));
            }
        }
        PageInfo<PetCrousel> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}