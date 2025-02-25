package com.luoyi.example.order.vo;

import lombok.Data;

@Data
public class OrderResult {

    public String orderNo;

    public OrderResult(String orderId) {
        this.orderNo = orderId;
    }
}
