package com.xw.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class PetAdoptionCenter {
    private Integer id;
    private Integer userId;
    private String userName;
    private String userImage;
    @NotNull(message = "宠物ID不能为空")
    private Integer petCenterId;

    @NotBlank(message = "宠物名称不能为空！")
    private String petCenterName;

    @NotBlank(message = "联系人联系方式！")
    private String userPhone;

    @NotBlank(message = "联系人地址！")
    private String userAddress;

    private Integer adoptionStatus;


    public void setUserInfo(User userInfo) {
        this.setUserId(userInfo.getId());
        this.setUserName(userInfo.getNickName());
        this.setUserImage(userInfo.getImageUrl());
    }
}
