package com.luoyi.example.order.handler;

import com.alibaba.fastjson2.annotation.JSONType;
import com.luoyi.example.order.context.OrderContext;
import com.luoyi.example.order.enums.SceneTypeEnum;
import com.luoyi.example.order.json.ClassNameSerializer;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Set;

/**
 * 消费风控 handler
 */
@Service
@JSONType(serializer = ClassNameSerializer.class)
public class ConsumeRiskHandler implements OrderHandler {

    // 注入卡券风控门面
//    private final ConsumeRiskFacade consumeRiskFacade;

    @Override
    public void handle(OrderContext context) {
        // 消费风控
        // consumeRiskFacade.consumeRisk(user.getAppCode(), user.getUserId(), ConsumeRiskScene.CARD, dto.getOrderAmount());
        System.out.println("消费风控处理");
    }

    @Override
    public Integer getSorted() {
        return 0;
    }

    @Override
    public Set<SceneTypeEnum> supportedSceneTypes() {
        return EnumSet.of(SceneTypeEnum.CARD);
    }

}
