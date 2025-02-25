package com.luoyi.example.order.context;

import com.luoyi.example.order.enums.OrderType;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class OrderContext {

    public OrderType orderType;

    public String orderId;

    // 扩展参数容器 (类型安全)
    private Map<AttributeKey<?>, Object> attributes = new ConcurrentHashMap<>();

    public <T> T getAttribute(AttributeKey<T> key) {
        return (T) attributes.get(key);
    }

    public <T> void setAttribute(AttributeKey<T> key, T value) {
        attributes.put(key, value);
    }
}
