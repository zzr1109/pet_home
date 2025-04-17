package com.xw.common;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.xw.dao.UserDao;
import com.xw.dao.UserTokenDao;
import com.xw.entity.User;
import com.xw.entity.UserToken;
import com.xw.exp.ServiceException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Objects;

@Component
public class TokenHandler {
    public static User getUserInfo() {
        UserTokenDao userTokenDao = SpringUtil.getBean(UserTokenDao.class);
        UserDao userDao = SpringUtil.getBean(UserDao.class);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(Constants.UNAUTHORIZED.getCode(), "token过期，请先登陆");
        }
        UserToken userTokenQuery =  new UserToken();
        userTokenQuery.setToken(token);
        List<UserToken> userTokenList = userTokenDao.selectAll(userTokenQuery);
        if (CollectionUtils.isEmpty(userTokenList)) {
            throw new ServiceException(Constants.UNAUTHORIZED.getCode(), "token过期，请先登陆");
        }
        User user = userDao.selectById(userTokenList.get(0).getUserId());
        if (Objects.isNull(user)) {
            throw new ServiceException(Constants.UNAUTHORIZED.getCode(), "用户不存在，请先登陆");
        }
        return user;
    }

    public static User getUserInfo(String token) {
        UserTokenDao userTokenDao = SpringUtil.getBean(UserTokenDao.class);
        UserDao userDao = SpringUtil.getBean(UserDao.class);
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(Constants.UNAUTHORIZED.getCode(), "token过期，请先登陆");
        }
        UserToken userToken = userTokenDao.selectByToken(token);
        if (Objects.isNull(userToken)) {
            throw new ServiceException(Constants.UNAUTHORIZED.getCode(), "token过期，请先登陆");
        }
        User user = userDao.selectById(userToken.getUserId());
        if (Objects.isNull(user)) {
            throw new ServiceException(Constants.UNAUTHORIZED.getCode(), "用户不存在，请先登陆");
        }
        return user;
    }
}
