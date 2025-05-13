package com.xw.entity;
import java.util.Date;
import java.math.BigDecimal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;
import com.xw.entity.User;
/**
 * 订单管理中心
 */
@Data
public class OrderCenter {
    private Integer id;
    @Schema(description = "订单总金额")
    private BigDecimal orderPrice;
    @Schema(description = "购买人ID")
    private Integer userId;
    @Schema(description = "购买人名称")
    private String userName;
    @Schema(description = "购买人头像")
    private String userImage;
    @Schema(description = "下单状态;-1已下单;0-已支付;1-已取消;2-已完成")
    private Integer status;
    @Schema(description = "支付方式")
    private String payMode;
    @Schema(description = "订单编号")
    private String orderNumber;
    public void setUserInfo(User user) {
        this.userId=user.getId();
        this.userName=user.getNickName();
        this.userImage=user.getImageUrl();
    }
    @Valid
    private List<OrderDetailCenter> orderDetailCenterList;
}