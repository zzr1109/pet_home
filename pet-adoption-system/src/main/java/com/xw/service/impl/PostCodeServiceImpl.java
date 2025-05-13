package com.xw.service.impl;
import com.xw.entity.PostCode;
import com.xw.dao.PostCodeDao;
import com.xw.service.PostCodeService;
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
 * 宠物圈帖子管理
 */
@Service("postCodeService")
public class PostCodeServiceImpl implements PostCodeService {
    @Resource
    private PostCodeDao postCodeDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @Override
    public PageInfo<PostCode> queryByPage(PageRequest<PostCode> pageRequest) {
        //开始分页
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<PostCode> list = this.postCodeDao.queryAll(pageRequest.getQ());
        for (PostCode postCode : list) {
            if (StringUtils.hasText(postCode.getImageUrlList())) {
                postCode.setMenuImages(Arrays.stream(postCode.getImageUrlList().split(",")).collect(Collectors.toList()));
            }
        }
        PageInfo<PostCode> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}