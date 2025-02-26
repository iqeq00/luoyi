package com.luoyi.example.order.vo;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderResVO {

    /**
     * 订单号
     */
    private List<String> orderNo;

    /**
     * 支付流水号
     */
    private String payNo;

    /**
     * 是否是0元单
     */
    private Boolean zeroOrder = true;

    /**
     * 外部应用下单收银台地址（只有外部下单才会有值）
     */
    private String returnUrl;
}
