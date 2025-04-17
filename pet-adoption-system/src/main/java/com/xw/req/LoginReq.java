package com.xw.req;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * 用户登陆请求类
 */
@Data
public class LoginReq {
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String password;
//    @NotBlank(message = "登陆角色不能为空")
    private String role;
}
