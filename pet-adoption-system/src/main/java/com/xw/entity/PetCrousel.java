package com.xw.entity;

import lombok.Data;
import java.util.List;

@Data
public class PetCrousel {
    private Integer id;
    //图片
    private String imageUrlList;
    //前段展示图片字段
    private List<String> menuImages;
    //0不显示1显示
    private  Integer showStatus;
}
