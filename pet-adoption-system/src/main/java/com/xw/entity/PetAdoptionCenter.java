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
 * 宠物领养管理
 */
@Data
public class PetAdoptionCenter {
    private Integer id;
    @Schema(description = "领养人ID")
    private Integer userId;
    @Schema(description = "领养人名称")
    private String userName;
    @Schema(description = "领养人头像")
    private String userImage;
    @Schema(description = "领养人联系方式")
    @NotBlank(message = "领养人联系方式不能为空!")
    private String userPhone;
    @Schema(description = "领养人地址")
    @NotBlank(message = "领养人地址不能为空!")
    private String userAddress;
    @Schema(description = "宠物ID")
    @NotNull(message = "宠物ID不能为空!")
    private Integer petCenterId;
    @Schema(description = "宠物名称")
    @NotBlank(message = "宠物名称不能为空!")
    private String petCenterName;
    @Schema(description = "领养状态;0-领养中;1-已归还;2-申请中")
    private Integer adoptionStatus;
    public void setUserInfo(User user) {
        this.userId=user.getId();
        this.userName=user.getNickName();
        this.userImage=user.getImageUrl();
    }
}