package com.xw.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

@Data
public class PetCenter {
    private Integer id;
    // 图片
    private String imageUrlList;
    // 前端展示图片字段
    private List<String> menuImages;
    @NotNull(message = "宠物类别ID不能为空")
    private Integer petCategoryId;
    @NotBlank(message = "宠物类别名称不能为空")
    private String petCategoryName;
    private Integer userId;
    private String userName;
    private Date createTime;
    private Integer adoptionStatus;
    @NotBlank(message = "宠物名称不能为空")
    private String petName;
    @NotNull(message = "宠物性别不能为空")
    private Integer petSex;
    @NotBlank(message = "宠物年龄不能为空")
    private String petAge;
    @NotBlank(message = "宠物描述不能为空")
    private String petRemark;

    public void setUserInfo(User userInfo) {
        this.setUserId(userInfo.getId());
        this.setUserName(userInfo.getNickName());
        this.setCreateTime(new Date());
    }
    private List<Integer> ids;
}
