package com.xw.entity;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PostCode {
    private Integer id;
    // 帖子内容
    @NotBlank(message = "帖子内容不能为空")
    private String content;
    private Date createTime;
    private Integer userId;
    private String userName;
    private String userImage;
    private String title;
    private String imageUrlList;
    // 前端展示数组
    private List<String> menuImages;

    public void setUserInfo(User userInfo) {
        this.setUserId(userInfo.getId());
        this.setUserName(userInfo.getUserName());
        this.setUserImage(userInfo.getImageUrl());
        this.setCreateTime(new Date());
    }
}
