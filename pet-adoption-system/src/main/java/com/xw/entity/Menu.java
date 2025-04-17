package com.xw.entity;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
/**
 * @Description (Menu)表实体类
 * @Version 1.0
 **/

/**
 * (Menu)实体类
 *
 * @author makejava
 * @since 2024-07-12 11:10:10
 */
@Data
@Schema(description = "菜单实体")
public class Menu implements Serializable {
    private static final long serialVersionUID = 859456060197721034L;
    @Schema(description = "主键ID")
    private Integer id;
    @Schema(description = "跳转路径 （为空时代表是一个父菜单）")
    private String path;
    @Schema(description = "路由名称")
    private String name;
    @Schema(description = "Vue文件所在的位置")
    private String filePath;
    @Schema(description = "0 需要令牌认证。1 不需要令牌认证")
    private Integer isToken;
    @Schema(description = "菜单的名称")
    @NotBlank(message = "菜单的名称不能为空")
    private String menuName;
    @Schema(description = "0 显示在左侧菜单。 1 不显示")
    @NotNull(message = "请告知是否显示在左侧菜单")
    private Integer isShowMenu;
    @Schema(description = "排序字段")
    @NotNull(message = "请设定排序数字")
    private Integer sort;
    @Schema(description = "父菜单ID")
    private Integer parentId;
    @Schema(description = "菜单图标")
    private String icon;

    /**
     * 子菜单数组
     */
    private List<Menu> children;

    private List<Integer> menuIds;
}

