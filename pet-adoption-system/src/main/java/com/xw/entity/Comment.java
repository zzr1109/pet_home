package com.xw.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Comment {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    // 评论内容
    @NotBlank(message = "评论内容不能为空！")
    private String content;
    // 评论人ID
    private Integer userId;
    // 评论人名称
    private String userName;
    // 评论人头像
    private String userImage;
    // 评论时间
    private Date createTime;
    @NotNull(message = "所评论的对象ID不能为空！")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long linkId;
    @NotNull(message = "评论类型不能为空")
    private Integer type;
    @NotNull(message = "类型ID不能为空")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long typeId;

    public void setUserInfo(User userInfo) {
        this.setUserId(userInfo.getId());
        this.setUserName(userInfo.getNickName());
        this.setUserImage(userInfo.getImageUrl());
        this.setCreateTime(new Date());
    }
}
