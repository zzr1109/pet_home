package com.xw.service;
import com.xw.entity.PetCrousel;
import com.xw.req.PageRequest;
import com.github.pagehelper.PageInfo;
/**
 * 宠物轮播图
 */
public interface PetCrouselService {
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    PageInfo<PetCrousel> queryByPage(PageRequest<PetCrousel> pageRequest);
}