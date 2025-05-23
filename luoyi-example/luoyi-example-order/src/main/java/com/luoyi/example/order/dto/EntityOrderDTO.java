package com.luoyi.example.order.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class EntityOrderDTO extends BaseOrderDTO {

    /**
     * 活动编码
     */
    private String activityCode;
    /**
     * 活动场次
     */
    private Integer activityScheduleNum;
    /**
     * 购物车凭证
     */
//    @NotBlank(message = "购物车凭证不能为空")
    private String receipt;
    /**
     * 地址code
     */
//    @NotBlank(message = "地址编码不能为空")
    private String addressCode;
    /**
     * 优惠券所属选品集
     */
    private List<String> groupCodes;

}
