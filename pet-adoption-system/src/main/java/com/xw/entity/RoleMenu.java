package com.xw.entity;
import java.io.Serializable;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * @Description (RoleMenu)表实体类
 * @Version 1.0
 **/

/**
 * (RoleMenu)实体类
 *
 * @author makejava
 * @since 2024-07-12 11:29:03
 */
@Data
@Schema(description = "角色菜单实体")
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = -78898780561134260L;
    @Schema(description = "主键ID")
    private Integer id;
    @Schema(description = "角色ID")
    private Integer roleId;
    @Schema(description = "菜单ID")
    private Integer menuId;

    /**
     * 菜单的id数组
     */
    private List<Integer> menuIds;
}

