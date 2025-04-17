package com.xw.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class UpdatePasswordReq {
    @Schema(description = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @Schema(description = "确认密码")
    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;
}
