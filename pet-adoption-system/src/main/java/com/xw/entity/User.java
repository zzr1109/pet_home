package com.xw.entity;

import com.xw.jsr303Group.RegisterGroup;
import com.xw.jsr303Group.SaveGroup;
import com.xw.jsr303Group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Data
@Schema(description = "用户实体")
public class User {
    @Schema(description = "主键ID")
    private Integer id;
    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空",groups = {RegisterGroup.class, SaveGroup.class, UpdateGroup.class})
    private String userName;
    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空",groups = {RegisterGroup.class})
    private String password;
    @Schema(description = "昵称")
    @NotBlank(message = "昵称不能为空",groups = {RegisterGroup.class, SaveGroup.class, UpdateGroup.class})
    private String nickName;
    @Schema(description = "邮箱")
    @NotBlank(message = "邮箱不能为空",groups = {RegisterGroup.class, SaveGroup.class, UpdateGroup.class})
    private String email;
    @Schema(description = "年龄")
    private Integer age;
    @Schema(description = "联系电话")
    private String phone;
    @Schema(description = "用户头像")
    @NotBlank(message = "用户头像不能为空",groups = {RegisterGroup.class, SaveGroup.class, UpdateGroup.class})
    private String imageUrl;
    @Schema(description = "用户角色")
    @NotBlank(message = "用户角色不能为空",groups = {RegisterGroup.class, SaveGroup.class, UpdateGroup.class})
    private String role;

    @Schema(description = "确认密码")
    @NotBlank(message = "确认密码不能为空",groups = RegisterGroup.class)
    private String confirmPassword;

    @Schema(description = "审核 0 未审核  1 审核通过  2 审核不通过")
    private Integer applyStatus;

    private List<Integer> ids;
}
