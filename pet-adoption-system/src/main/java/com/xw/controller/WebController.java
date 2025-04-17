package com.xw.controller;
import cn.hutool.crypto.digest.DigestUtil;
import com.xw.common.Constants;
import com.xw.common.Result;
import com.xw.common.TokenHandler;
import com.xw.dao.UserDao;
import com.xw.dao.UserTokenDao;
import com.xw.entity.User;
import com.xw.exp.ServiceException;
import com.xw.jsr303Group.RegisterGroup;
import com.xw.rep.LoginRep;
import com.xw.req.LoginReq;
import com.xw.req.UpdatePasswordReq;
import com.xw.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("web")
@Tag(name = "web模块")
public class WebController {

    @Resource
    private UserService userService;

    @Resource
    private UserTokenDao userTokenDao;

    @Resource
    private UserDao userDao;

    @PostMapping("register")
    @Operation(summary = "用户注册")
    public Result<?> register(@RequestBody @Validated(RegisterGroup.class) User user) {
        Boolean register = userService.register(user);
        if (register) {
            return new Result<>(Constants.SUCCESS);
        }
        return new Result<>(Constants.ERROR,"注册失败");
    }

    @PostMapping("login")
    @Operation(summary = "用户登陆")
    public Result<?> login(@RequestBody @Validated LoginReq loginReq) {
        LoginRep loginRep = userService.login(loginReq);
        return new Result<>(loginRep,Constants.SUCCESS);
    }

    @GetMapping("logout")
    @Operation(summary = "退出登陆")
    public Result<?> logout(HttpServletRequest httpServletRequest) {
        // 删除token
        userTokenDao.deleteByToken(httpServletRequest.getHeader("token"));
        return new Result<>(Constants.SUCCESS);
    }

    @GetMapping("getPersonInfo")
    @Operation(summary = "个人信息")
    public Result<?> getPersonInfo() {
        User userInfo = TokenHandler.getUserInfo();
        return new Result<>(userInfo,Constants.SUCCESS);
    }

    @PostMapping("updatePersonInfo")
    @Operation(summary = "修改个人信息")
    public Result<?> updatePersonInfo(@RequestBody User user) {
        userDao.updateById(user);
        return new Result<>(Constants.SUCCESS);
    }

    @PostMapping("updatePassword")
    @Operation(summary = "修改密码")
    public Result<?> updatePassword(@RequestBody @Validated UpdatePasswordReq updatePasswordReq) {
        User userQueryEmail = new User();
        userQueryEmail.setEmail(updatePasswordReq.getEmail());
        List<User> usersEmailList = userDao.selectAll(userQueryEmail);
        Optional<User> _find = usersEmailList.stream().filter(u -> u.getEmail().equals(updatePasswordReq.getEmail())).findAny();
        if (!_find.isPresent()) {
            throw new ServiceException(Constants.ERROR.getCode(), "找不到用户信息");
        }
        if (!updatePasswordReq.getPassword().equals(updatePasswordReq.getConfirmPassword())) {
            throw new ServiceException(Constants.ERROR.getCode(), "俩次密码输入不一样");
        }
        User updatePassword = new User();
        updatePassword.setPassword(DigestUtil.md5Hex(updatePasswordReq.getPassword()));
        updatePassword.setId(_find.get().getId());
        userDao.updateById(updatePassword);
        return new Result<>(Constants.SUCCESS);
    }
}
