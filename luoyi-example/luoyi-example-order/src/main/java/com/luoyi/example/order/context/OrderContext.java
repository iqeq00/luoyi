package com.luoyi.example.order.context;

import com.luoyi.example.order.enums.SceneTypeEnum;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 订单执行上下文
 *
 * @author yaojinchi
 */
@Data
public class OrderContext {

    public SceneTypeEnum sceneType;

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