package com.xw.service;
import com.xw.entity.GoodsCenter;
import com.xw.req.PageRequest;
import com.github.pagehelper.PageInfo;
/**
 * 商品中心
 */
public interface GoodsCenterService {
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    PageInfo<GoodsCenter> queryByPage(PageRequest<GoodsCenter> pageRequest);
}