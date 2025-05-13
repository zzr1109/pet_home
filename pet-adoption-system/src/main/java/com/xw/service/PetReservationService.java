package com.xw.service;
import com.xw.entity.PetReservation;
import com.xw.req.PageRequest;
import com.github.pagehelper.PageInfo;
/**
 * 宠物预约管理
 */
public interface PetReservationService {
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    PageInfo<PetReservation> queryByPage(PageRequest<PetReservation> pageRequest);
}