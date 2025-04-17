package com.xw.rep;

import com.xw.entity.Menu;
import lombok.Data;

import java.util.List;

@Data
public class LoginRep {
    private Integer userId;
    private String nickName;
    private String imageUrl;
    private String token;
    private String role;
    private String roleRemark;

    private List<Menu> menuList;
}
