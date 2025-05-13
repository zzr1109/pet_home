package com.xw.entity;
import java.util.Date;
import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xw.req.PageRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;
import com.xw.entity.User;
/**
 * 评论表
 */
@Data
public class Comment {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @Schema(description = "评论内容")
    private String content;
    @Schema(description = "评论人ID")
    private Integer userId;
    @Schema(description = "评论人名称")
    private String userName;
    @Schema(description = "评论人头像")
    private String userImage;
    @Schema(description = "评论时间")
    private Date createTime;
    @Schema(description = "所评论对象的ID")
    @NotNull(message = "所评论对象的ID不能为空!")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long linkId;
    @Schema(description = "评论类型;0-帖子评论;1-互评;2-商品评价")
    @NotNull(message = "评论类型不能为空!")
    private Integer type;
    @Schema(description = "类型ID")
    @NotNull(message = "类型ID不能为空!")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long typeId;
    public void setUserInfo(User user) {
        this.userId=user.getId();
        this.userName=user.getNickName();
        this.userImage=user.getImageUrl();
        this.createTime=new Date();
    }
    private List<Long> typeIds;
    // 当前页的分页数据
    private PageRequest<Comment> pageRequest;
    private List<Comment> children;
    // 被评人名称
    private String nameOfTheEvaluatedPerson;
    private List<Long> ids;
}