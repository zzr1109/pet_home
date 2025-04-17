package com.xw.controller;
import cn.hutool.crypto.digest.DigestUtil;
import com.xw.common.Constants;
import com.xw.common.Result;
import com.xw.dao.UserDao;
import com.xw.entity.User;
import com.xw.exp.ServiceException;
import com.xw.jsr303Group.SaveGroup;
import com.xw.jsr303Group.UpdateGroup;
import com.xw.req.PageRequest;
import com.xw.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
@Tag(name = "用户模块")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserDao userDao;

    @PostMapping("page")
    @Operation(summary = "用户列表")
    public Result<?> page(@RequestBody PageRequest<User> pageRequest) {
        return new Result<>(userService.page(pageRequest), Constants.SUCCESS);
    }


    @PostMapping("saveOrUpdate")
    @Operation(summary = "新增或修改用户")
    public Result<?> saveOrUpdate(@RequestBody @Validated({SaveGroup.class, UpdateGroup.class}) User user) {
        if (user.getId() == null) {
            User userQueryEmail = new User();
            userQueryEmail.setEmail(user.getEmail());
            List<User> usersEmailList = userDao.selectAll(userQueryEmail);
            Optional<User> _find = usersEmailList.stream().filter(u -> u.getEmail().equals(user.getEmail())).findAny();
            if (_find.isPresent()) {
                throw new ServiceException(Constants.ERROR.getCode(), "邮箱已注册");
            }

            user.setPassword("123456"); // 默认密码为123456
            user.setPassword(DigestUtil.md5Hex(user.getPassword()));
            userDao.insert(user);
        } else {
            user.setPassword(null);
            userDao.updateById(user);
        }
        return new Result<>(Constants.SUCCESS);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "通过ID删除用户信息")
    public Result<?> deleteById(@PathVariable("id") Integer id) {
        userDao.deleteById(id);
        return new Result<>(Constants.SUCCESS);
    }

    @PostMapping("deleteBatchByIds")
    @Operation(summary = "通过ID批量删除用户信息")
    public Result<?> deleteBatchByIds(@RequestBody List<Integer> ids) {
        userDao.deleteBatchByIds(ids);
        return new Result<>(Constants.SUCCESS);
    }

}
