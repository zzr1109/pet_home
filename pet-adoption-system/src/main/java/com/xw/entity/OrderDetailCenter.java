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
 * 订单详情
 */
@Data
public class OrderDetailCenter {
    private Integer id;
    @Schema(description = "商品名称")
    @NotBlank(message = "商品名称不能为空!")
    private String goodsName;
    @Schema(description = "购买数量")
    @NotNull(message = "购买数量不能为空!")
    private Integer number;
    @Schema(description = "购买单价")
    @NotNull(message = "购买单价不能为空!")
    private BigDecimal singlePrice;
    @Schema(description = "商品ID")
    @NotNull(message = "商品ID不能为空!")
    private Integer goodsCenterId;
    @Schema(description = "订单ID")
    private Integer orderCenterId;
    @Schema(description = "订单编号")
    private String orderNumber;
    public void setUserInfo(User user) {
    }

    private GoodsCenter goodsCenter;
}