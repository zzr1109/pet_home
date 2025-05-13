package com.xw.service.impl;
import com.xw.entity.GoodsCenter;
import com.xw.dao.GoodsCenterDao;
import com.xw.service.GoodsCenterService;
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
 * 商品中心
 */
@Service("goodsCenterService")
public class GoodsCenterServiceImpl implements GoodsCenterService {
    @Resource
    private GoodsCenterDao goodsCenterDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @Override
    public PageInfo<GoodsCenter> queryByPage(PageRequest<GoodsCenter> pageRequest) {
        //开始分页
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<GoodsCenter> list = this.goodsCenterDao.queryAll(pageRequest.getQ());
        for (GoodsCenter goodsCenter : list) {
            if (StringUtils.hasText(goodsCenter.getImageUrlList())) {
                goodsCenter.setMenuImages(Arrays.stream(goodsCenter.getImageUrlList().split(",")).collect(Collectors.toList()));
            }
        }
        PageInfo<GoodsCenter> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}