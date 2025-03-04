package com.luoyi.example.order.handler;

import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.annotation.JSONType;
import com.luoyi.example.order.context.OrderContext;
import com.luoyi.example.order.enums.SceneTypeEnum;

import java.util.Set;

/**
 * 责任链处理器接口
 */
@JSONType(serializeFeatures = {JSONWriter.Feature.WriteClassName})
public interface OrderHandler {

    // 处理方法
    void handle(OrderContext context);

    // 排序
    Integer getSorted();

    // 支持处理的订单场景
    Set<SceneTypeEnum> supportedSceneTypes();

    @Override
    String toString();

}