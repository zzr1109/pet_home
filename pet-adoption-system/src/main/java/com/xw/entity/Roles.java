package com.xw.entity;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
/**
 * @Description (Roles)表实体类
 * @Version 1.0
 **/

/**
 * (Roles)实体类
 *
 * @author makejava
 * @since 2024-07-12 11:52:19
 */
@Data
@Schema(description = "角色实体")
public class Roles implements Serializable {
    private static final long serialVersionUID = -57482597033969262L;
    private Integer id;
    @Schema(description = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String name;
    @Schema(description = "角色描述")
    private String remark;
}

