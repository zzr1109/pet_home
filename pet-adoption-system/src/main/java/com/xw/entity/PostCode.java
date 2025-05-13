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
 * 宠物圈帖子管理
 */
@Data
public class PostCode {
    private Integer id;
    @Schema(description = "发布内容")
    @NotBlank(message = "发布内容不能为空!")
    private String content;
    @Schema(description = "发布时间")
    private Date createTime;
    private String imageUrlList;
    private List<String> menuImages;
    @Schema(description = "发布人ID")
    private Integer userId;
    @Schema(description = "发布人名称")
    private String userName;
    @Schema(description = "发布人头像")
    private String userImage;
    @Schema(description = "发布标题")
    private String title;
    public void setUserInfo(User user) {
        this.createTime=new Date();
        this.userId=user.getId();
        this.userName=user.getNickName();
        this.userImage=user.getImageUrl();
    }
}