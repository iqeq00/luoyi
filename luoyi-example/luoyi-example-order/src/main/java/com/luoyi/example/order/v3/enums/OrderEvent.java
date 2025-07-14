package com.luoyi.example.order.v3.enums;

import lombok.Getter;

/**
 * 订单事件枚举（包含英文和中文描述）
 */
@Getter
public enum OrderEvent {
    // 事件定义：枚举值(英文描述, 中文描述)
    SUBMIT("submit", "提交订单"),
    PAY("pay", "支付订单"),
    SHIP("ship", "发货"),
    DELIVER("deliver", "确认送达"),
    CANCEL("cancel", "取消订单"),
    REFUND("refund", "申请退款");

    private final String enDesc;
    private final String cnDesc;

    OrderEvent(String enDesc, String cnDesc) {
        this.enDesc = enDesc;
        this.cnDesc = cnDesc;
    }

    public static OrderEvent getInstance(String event){
        OrderEvent[] instances = OrderEvent.values();
        for (OrderEvent instance : instances) {
            if (instance.getEnDesc().equals(event)) {
                return instance;
            }
        }
        return null;
    }
}
