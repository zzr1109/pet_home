package com.xw.service.impl;
import com.xw.entity.PetAdoptionCenter;
import com.xw.dao.PetAdoptionCenterDao;
import com.xw.service.PetAdoptionCenterService;
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
 * 宠物领养管理
 */
@Service("petAdoptionCenterService")
public class PetAdoptionCenterServiceImpl implements PetAdoptionCenterService {
    @Resource
    private PetAdoptionCenterDao petAdoptionCenterDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @Override
    public PageInfo<PetAdoptionCenter> queryByPage(PageRequest<PetAdoptionCenter> pageRequest) {
        //开始分页
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<PetAdoptionCenter> list = this.petAdoptionCenterDao.queryAll(pageRequest.getQ());
        PageInfo<PetAdoptionCenter> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}