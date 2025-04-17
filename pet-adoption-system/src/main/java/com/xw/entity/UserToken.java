package com.xw.entity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.Date;

@Data
@Schema(description = "用户令牌模块")
public class UserToken {
    @Schema(description = "主键ID")
    private Integer id;
    @Schema(description = "用户令牌")
    private String token;
    @Schema(description = "用户ID")
    private Integer userId;
    @Schema(description = "过期时间")
    private Integer overdue;
    @Schema(description = "令牌创建时间")
    private Date createTime;
}
