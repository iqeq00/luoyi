package com.luoyi.example.order.factory;

import com.alibaba.fastjson2.JSON;
import com.luoyi.example.order.enums.SceneTypeEnum;
import com.luoyi.example.order.handler.OrderHandler;
import com.luoyi.example.order.handler.OrderHandlerChain;
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
 *
 * @author yaojinchi
 */
@Slf4j
@Component
public class OrderHandlerFactory {

    @Autowired
    private List<OrderHandler> handlers;

    private Map<SceneTypeEnum, List<OrderHandler>> handlerCache = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {

        Arrays.stream(SceneTypeEnum.values()).forEach(sceneType -> {
            List<OrderHandler> chains = handlers.stream()
                    .filter(handler -> handler.supportedSceneTypes().contains(sceneType))
                    .sorted(Comparator.comparingInt(OrderHandler::getSorted))
                    .collect(Collectors.toList());
            handlerCache.put(sceneType, chains);
        });
        log.info("订单全场景责任链条:{}", JSON.toJSONString(handlerCache));
    }

    public OrderHandlerChain createChain(SceneTypeEnum sceneType) {
        return new OrderHandlerChain(handlerCache.get(sceneType));
    }

}