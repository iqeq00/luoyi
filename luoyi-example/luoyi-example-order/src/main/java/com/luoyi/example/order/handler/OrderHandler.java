package com.luoyi.example.order.handler;

import com.luoyi.example.order.context.OrderContext;
import com.luoyi.example.order.enums.SceneTypeEnum;

import java.util.Set;

/**
 * 订单处理器
 *
 * @author yaojinchi
 */
public interface OrderHandler {

    // 处理方法
    void handle(OrderContext context);

    // 排序
    Integer getSorted();

    // 支持处理的订单场景
    Set<SceneTypeEnum> supportedSceneTypes();

}