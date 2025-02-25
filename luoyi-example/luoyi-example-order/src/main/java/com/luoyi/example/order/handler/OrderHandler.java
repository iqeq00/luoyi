package com.luoyi.example.order.handler;

import com.luoyi.example.order.context.OrderContext;
import com.luoyi.example.order.dto.BaseOrderDTO;
import com.luoyi.example.order.enums.OrderType;

import java.util.Set;

/**
 * 责任链处理器接口
 */
public interface OrderHandler {

    void handle(OrderContext context);

    // 排序
    int getOrder();

    // 支持处理的订单类型
    Set<OrderType> supportedTypes();
}
