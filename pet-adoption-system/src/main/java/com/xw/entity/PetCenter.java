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
 * 宠物中心
 */
@Data
public class PetCenter {
    private Integer id;
    @Schema(description = "宠物类别ID")
    @NotNull(message = "宠物类别ID不能为空!")
    private Integer petCategoryId;
    @Schema(description = "宠物类别名称")
    @NotBlank(message = "宠物类别名称不能为空!")
    private String petCategoryName;
    private String imageUrlList;
    private List<String> menuImages;
    @Schema(description = "创建人ID")
    private Integer userId;
    @Schema(description = "创建人名称")
    private String userName;
    @Schema(description = "创建人头像")
    private String userImage;
    @Schema(description = "创建时间")
    private Date createTime;
    @Schema(description = "领养状态;0-待领养;1-已领养")
    private Integer adoptionStatus;
    @Schema(description = "宠物姓名")
    @NotBlank(message = "宠物姓名不能为空!")
    private String petName;
    @Schema(description = "宠物性别;0-公;1-母;2-未知")
    @NotNull(message = "宠物性别不能为空!")
    private Integer petSex;
    @Schema(description = "宠物年龄")
    @NotBlank(message = "宠物年龄不能为空!")
    private String petAge;
    @Schema(description = "宠物描述")
    @NotBlank(message = "宠物描述不能为空!")
    private String petRemark;
    public void setUserInfo(User user) {
        this.userId=user.getId();
        this.userName=user.getNickName();
        this.userImage=user.getImageUrl();
        this.createTime=new Date();
    }

    private List<Integer> ids;
    @Schema(description = "明星值")
    private Integer starNum;

}