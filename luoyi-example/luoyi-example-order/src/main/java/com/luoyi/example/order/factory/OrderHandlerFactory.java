package com.luoyi.example.order.factory;

import com.alibaba.fastjson2.JSON;
import com.luoyi.example.order.enums.OrderType;
import com.luoyi.example.order.enums.SceneTypeEnum;
import com.luoyi.example.order.handler.OrderHandler;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 订单责任链工厂
 */
@Slf4j
@Component
public class OrderHandlerFactory {

    // 自动注入所有处理器
    @Autowired
    private List<OrderHandler> handlers;

    private static Map<SceneTypeEnum, List<OrderHandler>> handlerCache = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        // 预构建不同订单类型的处理器链
        Arrays.stream(SceneTypeEnum.values()).forEach(sceneType -> {
            List<OrderHandler> chains = handlers.stream()
                .filter(handler -> handler.supportedSceneTypes().contains(sceneType))
                .sorted(Comparator.comparingInt(OrderHandler::getSorted))
                .collect(Collectors.toList());
            handlerCache.put(sceneType, chains);
        });
        log.info("订单各场景责任链:{}", JSON.toJSONString(handlerCache));
    }

    public OrderHandlerChain createChain(SceneTypeEnum sceneType) {
        return new OrderHandlerChain(handlerCache.get(sceneType));
    }

}
