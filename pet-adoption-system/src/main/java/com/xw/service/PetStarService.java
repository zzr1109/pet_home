package com.xw.service;
import com.xw.entity.PetStar;
import com.xw.req.PageRequest;
import com.github.pagehelper.PageInfo;
/**
 * 宠物明星值管理
 */
public interface PetStarService {
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    PageInfo<PetStar> queryByPage(PageRequest<PetStar> pageRequest);
}