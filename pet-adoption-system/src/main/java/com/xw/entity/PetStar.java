package com.xw.entity;
import java.util.Date;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;
import com.xw.entity.User;
/**
 * 宠物明星值管理
 */
@Data
public class PetStar {
    private Integer id;
    @Schema(description = "宠物明星值")
    private Integer starNum;
    @Schema(description = "宠物ID")
    @NotNull(message = "宠物ID不能为空!")
    private Integer petCenterId;
    @Schema(description = "宠物名称")
    @NotBlank(message = "宠物名称不能为空!")
    private String petName;
    public void setUserInfo(User user) {
    }
    private List<Integer> petCenterIds;
}