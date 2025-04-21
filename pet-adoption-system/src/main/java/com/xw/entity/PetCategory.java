package com.xw.entity;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PetCategory {
    private Integer id;
    // 宠物类别
    @NotBlank(message = "宠物类别不能为空")
    private String category;

    public void setUserInfo(User userInfo) {
    }
}
