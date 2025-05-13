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
 * 宠物类别
 */
@Data
public class PetCategory {
    private Integer id;
    @Schema(description = "宠物类别")
    @NotBlank(message = "宠物类别不能为空!")
    private String category;
    public void setUserInfo(User user) {
    }
}