package com.luoyi.example.order.v3.enums;

import lombok.Getter;

/**
 * 订单状态枚举（包含英文和中文描述）
 */
@Getter
public enum OrderStatus {
    // 状态定义：枚举值(英文描述, 中文描述)
    CREATED("created", "已创建"),
    WAIT_PAYMENT("wait_payment", "待支付"),
    PAID("paid", "已支付"),
    SHIPPED("shipped", "已发货"),
    DELIVERED("delivered", "已送达"),
    CANCELLED("cancelled", "已取消"),
    REFUNDED("refunded", "已退款");

    // 英文描述（可用于日志、国际化等）
    private final String enDesc;
    // 中文描述（可用于前端展示）
    private final String cnDesc;

    // 构造方法：初始化双描述
    OrderStatus(String enDesc, String cnDesc) {
        this.enDesc = enDesc;
        this.cnDesc = cnDesc;
    }

    // 可选：根据英文描述反查枚举（用于从数据库/前端参数转换）
    public static OrderStatus getByEnDesc(String enDesc) {
        for (OrderStatus status : values()) {
            if (status.enDesc.equals(enDesc)) {
                return status;
            }
        }
        return null;
    }
}
