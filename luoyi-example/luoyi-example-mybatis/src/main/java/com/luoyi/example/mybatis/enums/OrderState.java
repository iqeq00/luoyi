package com.luoyi.example.mybatis.enums;

import lombok.Getter;

@Getter
public enum OrderState {

    INIT("init", "提交订单"),
    CREATE("create", "待支付"),
    CANCEL_PAY("cancelPay", "取消支付"),
    CANCEL_ORDER("cancelOrder", "取消订单"),
    PAID("paid", "已支付"),
    COMPLETED("completed", "已完成"),
    REFUSAL("refusal", "商家拒单");

    private final String code;
    private final String desc;

    OrderState(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static OrderState getByCode(String code) {
        for (OrderState state : values()) {
            if (state.code.equals(code)) {
                return state;
            }
        }
        return null;
    }

}
