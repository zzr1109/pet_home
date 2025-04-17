package com.xw.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.xw.entity.User;
import com.xw.rep.LoginRep;
import com.xw.req.LoginReq;
import com.xw.req.PageRequest;

public interface UserService {
    // 用户注册
    Boolean register(User user);

    LoginRep login(LoginReq loginReq);

    PageInfo<User> page(PageRequest<User> pageRequest);
}
