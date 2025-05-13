package com.xw.service;
import com.xw.entity.PetAdoptionCenter;
import com.xw.req.PageRequest;
import com.github.pagehelper.PageInfo;
/**
 * 宠物领养管理
 */
public interface PetAdoptionCenterService {
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    PageInfo<PetAdoptionCenter> queryByPage(PageRequest<PetAdoptionCenter> pageRequest);
}