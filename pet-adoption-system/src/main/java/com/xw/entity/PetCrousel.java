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
 * 宠物轮播图
 */
@Data
public class PetCrousel {
    private Integer id;
    private String imageUrlList;
    private List<String> menuImages;
    @Schema(description = "是否显示;0-不显示;1-显示")
    private Integer showStatus;
    public void setUserInfo(User user) {
    }
}