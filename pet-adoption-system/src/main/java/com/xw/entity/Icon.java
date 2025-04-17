package com.xw.entity;

import java.io.Serializable;
import java.sql.JDBCType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * @Description (Icon)表实体类
 * @Version 1.0
 **/

/**
 * (Icon)实体类
 *
 * @author makejava
 * @since 2024-07-12 11:03:21
 */
@Data
@Schema(description = "图标实体")
public class Icon implements Serializable {
    private static final long serialVersionUID = -14872042010365355L;
    @Schema(description = "主键ID")
    private Integer id;
    @Schema(description = "内容")
    private String value;
}

