package com.luoyi.example.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class CardOrderDTO extends BaseOrderDTO {

    /**
     * 活动编码
     */
    private String activityCode;
    /**
     * 活动场次
     */
    private Integer activityScheduleNum;
    /**
     * 订单支付金额
     */
//    @NotNull(message = "订单支付不能为空")
    private Long orderAmount;
    /**
     * 卡券信息
     */
//    @Valid
//    @NotNull(message = "卡券信息不能为空")
    private CardInfoDTO cardInfo;

}
