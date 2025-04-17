package com.xw.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xw.common.Constants;
import com.xw.dao.*;
import com.xw.entity.*;
import com.xw.exp.ServiceException;
import com.xw.rep.LoginRep;
import com.xw.req.LoginReq;
import com.xw.req.PageRequest;
import com.xw.service.MenuService;
import com.xw.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    @Resource
    private UserDao userDao;

    @Resource
    private UserTokenDao userTokenDao;

    @Resource
    private MenuService menuService;

    @Resource
    private RoleMenuDao roleMenuDao;

    @Resource
    private RolesDao rolesDao;


    @Override
    public Boolean register(User user) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new ServiceException(Constants.ERROR.getCode(), "俩次密码输入不一样");
        }

        User userQuery = new User();
        userQuery.setUserName(user.getUserName());
        List<User> users = userDao.selectAll(userQuery);
        if (!CollectionUtils.isEmpty(users)) {
            throw new ServiceException(Constants.ERROR.getCode(), "用户已存在");
        }

        User userQueryEmail = new User();
        userQueryEmail.setEmail(user.getEmail());
        List<User> usersEmailList = userDao.selectAll(userQueryEmail);
        Optional<User> _find = usersEmailList.stream().filter(u -> u.getEmail().equals(user.getEmail())).findAny();
        if (_find.isPresent()) {
            throw new ServiceException(Constants.ERROR.getCode(), "邮箱已注册");
        }
        user.setPassword(DigestUtil.md5Hex(user.getPassword()));
        userDao.insert(user);

        return true;
    }

    @Override
    @Transactional
    public LoginRep login(LoginReq loginReq) {
//        if (StrUtil.isBlank(loginReq.getRole())) {
//            throw new ServiceException(Constants.PARAMETER_ERROR.getCode(), "请选择角色");
//        }

        User userQuery = new User();
        userQuery.setUserName(loginReq.getUserName());
        userQuery.setPassword(DigestUtil.md5Hex(loginReq.getPassword()));
        userQuery.setRole(loginReq.getRole());
        User user = userDao.selectByUserNameAndPassword(userQuery);

        if (Objects.isNull(user)) {
            throw new ServiceException(Constants.UNAUTHORIZED.getCode(),"用户不存在或用户密码错误");
        }

        LoginRep loginRep = new LoginRep();
        loginRep.setUserId(user.getId());
        loginRep.setNickName(user.getNickName());
        loginRep.setImageUrl(user.getImageUrl());
        long time = new Date().getTime();
        String token = DigestUtil.md5Hex(user.getId() + "_" + time);
        loginRep.setToken(token);
        loginRep.setRole(user.getRole());

        Roles roles = rolesDao.selectByRoleName(user.getRole());
        loginRep.setRoleRemark(roles.getRemark());

        // 在新增token之前把该用户的老得token删掉
        UserToken userTokenQuery =  new UserToken();
        userTokenQuery.setUserId(user.getId());
        List<UserToken> userTokenList = userTokenDao.selectAll(userTokenQuery);
        if (!CollectionUtils.isEmpty(userTokenList)) {
            for (UserToken userToken : userTokenList) {
                // 删除
                userTokenDao.deleteById(userToken.getId());
            }
        }
        // 把数据新增到 userToken 表
        UserToken userToken = new UserToken();
        userToken.setUserId(user.getId());
        userToken.setToken(token);
        userToken.setOverdue(-1); // 默认永不过期
        userToken.setCreateTime(new Date());
        userTokenDao.insert(userToken);
        // 判断登陆的用户是什么角色
        List<Menu> menus = new ArrayList<>();
        if (user.getRole().equals(ROLE_ADMIN)) {
            menus = menuService.menuList();
        } else {
            menus = menuService.menuListByRoleName(user.getRole());
        }
        loginRep.setMenuList(menus);
        return loginRep;
    }

    @Override
    public PageInfo<User> page(PageRequest<User> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<User> users = userDao.selectAll(pageRequest.getQ());
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }
}
