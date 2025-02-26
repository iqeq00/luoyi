package com.luoyi.example.order.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "sceneType")
public class BaseOrderDTO {

    /**
     * 场景类型
     */
    @NotBlank(message = "场景类型不能为空")
    private String sceneType;
//    /**
//     * 活动编码
//     */
//    private String activityCode;
//    /**
//     * 活动场次
//     */
//    private Integer activityScheduleNum;
//    /**
//     * 订单支付金额
//     */
//    @NotNull(message = "订单支付不能为空")
//    private Long orderAmount;
    /**
     * 订单备注
     */
    private String note;
    /**
     * 专项id
     */
    private Integer itemId;
    /**
     * 优惠券code
     */
    private String couponCode;
    /**
     * 红包
     */
    private List<Integer> rpItemIds;
    /**
     * 版本标识 true 新版本  false 旧版本
     */
    @NotNull(message = "收银台版本不能为空")
    private Boolean versionFlag = false;
    /**
     * 是否是外部应用
     */
    @NotNull(message = "外部应用表示不能为空")
    private Boolean isOutApp = false;
}
