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
 * 商品中心
 */
@Data
public class GoodsCenter {
    private Integer id;
    @Schema(description = "商品名称")
    @NotBlank(message = "商品名称不能为空!")
    private String name;
    private String imageUrlList;
    private List<String> menuImages;
    @Schema(description = "发布人ID")
    private Integer userId;
    @Schema(description = "发布人名称")
    private String userName;
    @Schema(description = "发布人头像")
    private String userImage;
    @Schema(description = "商品价格")
    @NotNull(message = "商品价格不能为空!")
    private BigDecimal price;
    @Schema(description = "库存数量")
    private Integer stockNumber;
    @Schema(description = "已购数量")
    private Integer buyNumber;
    @Schema(description = "商品描述")
    @NotBlank(message = "商品描述不能为空!")
    private String remark;
    public void setUserInfo(User user) {
        this.userId=user.getId();
        this.userName=user.getNickName();
        this.userImage=user.getImageUrl();
    }
    private List<Integer> ids;
}