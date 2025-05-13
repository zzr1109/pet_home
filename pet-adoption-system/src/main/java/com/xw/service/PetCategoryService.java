package com.xw.service;
import com.xw.entity.PetCategory;
import com.xw.req.PageRequest;
import com.github.pagehelper.PageInfo;
/**
 * 宠物类别
 */
public interface PetCategoryService {
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    PageInfo<PetCategory> queryByPage(PageRequest<PetCategory> pageRequest);
}