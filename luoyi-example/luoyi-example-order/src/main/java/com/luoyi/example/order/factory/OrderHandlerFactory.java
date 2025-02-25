package com.luoyi.example.order.factory;

import com.luoyi.example.order.enums.OrderType;
import com.luoyi.example.order.handler.OrderHandler;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class OrderHandlerFactory {

    // 自动注入所有处理器
    @Autowired
    private List<OrderHandler> handlers;

    private final static Map<OrderType, List<OrderHandler>> handlerCache = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        // 预构建不同订单类型的处理器链
        Arrays.stream(OrderType.values()).forEach(type -> {
            List<OrderHandler> chain = handlers.stream()
                    .filter(handler -> handler.supportedTypes().contains(type))
                    .sorted(Comparator.comparingInt(OrderHandler::getOrder))
                    .collect(Collectors.toList());
            handlerCache.put(type, chain);
        });
    }

    public static OrderHandlerChain createChain(OrderType type) {
        return new OrderHandlerChain(handlerCache.get(type));
    }
}
