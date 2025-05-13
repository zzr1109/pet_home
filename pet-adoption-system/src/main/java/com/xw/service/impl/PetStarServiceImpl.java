package com.xw.service.impl;
import com.xw.entity.PetStar;
import com.xw.dao.PetStarDao;
import com.xw.service.PetStarService;
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
 * 宠物明星值管理
 */
@Service("petStarService")
public class PetStarServiceImpl implements PetStarService {
    @Resource
    private PetStarDao petStarDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @Override
    public PageInfo<PetStar> queryByPage(PageRequest<PetStar> pageRequest) {
        //开始分页
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<PetStar> list = this.petStarDao.queryAll(pageRequest.getQ());
        PageInfo<PetStar> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}