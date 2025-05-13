package com.xw.service;
import com.xw.entity.PetCenter;
import com.xw.req.PageRequest;
import com.github.pagehelper.PageInfo;
/**
 * 宠物中心
 */
public interface PetCenterService {
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    PageInfo<PetCenter> queryByPage(PageRequest<PetCenter> pageRequest);
}