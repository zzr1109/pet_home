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
 * 宠物预约管理
 */
@Data
public class PetReservation {
    private Integer id;
    @Schema(description = "预约人")
    private Integer userId;
    @Schema(description = "预约人姓名")
    private String userName;
    @Schema(description = "预约人头像")
    private String userImage;
    @Schema(description = "预约时间")
    private Date createTime;
    @Schema(description = "领养宣言")
    @NotBlank(message = "领养宣言不能为空!")
    private String remark;
    @Schema(description = "宠物ID")
    @NotNull(message = "宠物ID不能为空!")
    private Integer petCenterId;
    @Schema(description = "宠物名称")
    @NotBlank(message = "宠物名称不能为空!")
    private String petCenterName;
    @Schema(description = "预约状态;0-预约中;1-已预约;2-已领养;3-取消预约;4-放弃领养")
    private Integer status;
    @Schema(description = "领养人联系方式")
    @NotBlank(message = "领养人联系方式不能为空!")
    private String userPhone;
    @Schema(description = "领养人地址")
    @NotBlank(message = "领养人地址不能为空!")
    private String userAddress;
    public void setUserInfo(User user) {
        this.userId=user.getId();
        this.userName=user.getNickName();
        this.userImage=user.getImageUrl();
        this.createTime=new Date();
    }
    // 是否领养
    private Boolean whetherToAdoptOrNot;
}