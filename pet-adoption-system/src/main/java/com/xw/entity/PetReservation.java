package com.xw.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class PetReservation {
    private Integer id;
    // 评论人ID
    private Integer userId;
    // 评论人名称
    private String userName;
    // 评论人头像
    private String userImage;
    // 评论时间
    private Date createTime;
    @NotBlank(message = "领养宣言不能为空！")
    private String remark;

    @NotNull(message = "宠物ID不能为空")
    private Integer petCenterId;

    @NotBlank(message = "宠物名称不能为空！")
    private String petCenterName;

    private Integer status;

    @NotBlank(message = "联系人联系方式！")
    private String userPhone;

    @NotBlank(message = "联系人地址！")
    private String userAddress;


    public void setUserInfo(User userInfo) {
        this.setUserId(userInfo.getId());
        this.setUserName(userInfo.getNickName());
        this.setUserImage(userInfo.getImageUrl());
        this.setCreateTime(new Date());
    }
}
