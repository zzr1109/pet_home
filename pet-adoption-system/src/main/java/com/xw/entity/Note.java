package com.xw.entity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "公告模块")
public class Note {
    @Schema(description = "主键ID")
    private Integer id;

    @Schema(description = "发送内容",required = true)
    @NotBlank(message = "发送内容不能为空")
    private String content;

    @Schema(description = "发送时间")
    private Date createTime;

    @Schema(description = "用户ID")
    private Integer userId;

    @Schema(description = "用户名称")
    private String userName;

    @Schema(description = "用户头像")
    private String userImage;
}
