package com.xw.service.impl;
import com.xw.entity.PetCategory;
import com.xw.dao.PetCategoryDao;
import com.xw.service.PetCategoryService;
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
 * 宠物类别
 */
@Service("petCategoryService")
public class PetCategoryServiceImpl implements PetCategoryService {
    @Resource
    private PetCategoryDao petCategoryDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @Override
    public PageInfo<PetCategory> queryByPage(PageRequest<PetCategory> pageRequest) {
        //开始分页
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<PetCategory> list = this.petCategoryDao.queryAll(pageRequest.getQ());
        PageInfo<PetCategory> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}