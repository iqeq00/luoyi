package com.luoyi.example.mybatis.enums;

import lombok.Getter;

@Getter
public enum OrderEvent {

    CREATE_ORDER("createOrder", "创建订单"),
    PAY("pay", "支付"),
    CANCEL_PAY("cancelPay", "取消支付"),
    CANCEL_ORDER("cancelOrder", "取消订单"),
    COMPLETE("complete", "完成订单"),
    REFUSE("refuse", "拒单");

    private final String code;
    private final String desc;

    OrderEvent(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static OrderEvent getByCode(String code) {
        for (OrderEvent event : values()) {
            if (event.code.equals(code)) {
                return event;
            }
        }
        return null;
    }

}