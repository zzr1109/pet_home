package com.xw.rep;


import com.xw.entity.Menu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class RoleByMenuRep {
    @Schema(description = "已经存在的菜单")
    private List<Integer> checkedKeys;

    @Schema(description = "所有的菜单")
    private List<Menu> menus;
}
