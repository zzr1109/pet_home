package com.xw.config;

import cn.hutool.core.util.StrUtil;
import com.xw.common.Constants;
import com.xw.dao.UserTokenDao;
import com.xw.entity.UserToken;
import com.xw.exp.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.annotation.Resource;

import java.util.List;
import java.util.Objects;

public class Md5TokenInterceptor implements HandlerInterceptor {

    @Resource
    private UserTokenDao userTokenDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1 从请求头获取token
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(Constants.UNAUTHORIZED.getCode(), "token过期重新登陆");
        }
        // 2 查询数据库进行比对
        UserToken userTokenQuery =  new UserToken();
        userTokenQuery.setToken(token);
        List<UserToken> userTokenList = userTokenDao.selectAll(userTokenQuery);
        if (CollectionUtils.isEmpty(userTokenList)) {
            throw new ServiceException(Constants.UNAUTHORIZED.getCode(), "token过期重新登陆");
        }
        return true;
    }
}
