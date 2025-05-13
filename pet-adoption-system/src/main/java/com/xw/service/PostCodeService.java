package com.xw.service;
import com.xw.entity.PostCode;
import com.xw.req.PageRequest;
import com.github.pagehelper.PageInfo;
/**
 * 宠物圈帖子管理
 */
public interface PostCodeService {
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    PageInfo<PostCode> queryByPage(PageRequest<PostCode> pageRequest);
}