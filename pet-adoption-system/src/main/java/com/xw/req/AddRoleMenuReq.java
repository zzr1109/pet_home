package com.xw.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
public class AddRoleMenuReq {
    @Schema(description = "角色ID")
    @NotNull(message = "请先选择角色")
    private Integer roleId;
    private List<Integer> menuIds;
}
