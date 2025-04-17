package com.xw.entity;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * @Description (UserRole)表实体类
 * @Version 1.0
 **/

/**
 * (UserRole)实体类
 *
 * @author makejava
 * @since 2024-07-12 11:57:24
 */
@Data
@Schema(description = "用户角色实体")
public class UserRole implements Serializable {
    private static final long serialVersionUID = -86351592979879657L;
    private Integer id;
    @Schema(description = "用户ID")
    private Integer userId;
    @Schema(description = "角色ID")
    private Integer roleId;
}

