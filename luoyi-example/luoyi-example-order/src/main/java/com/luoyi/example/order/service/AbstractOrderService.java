package com.luoyi.example.order.service;

import com.luoyi.example.order.context.OrderContext;
import com.luoyi.example.order.dto.BaseOrderDTO;
import com.luoyi.example.order.enums.OrderType;
import com.luoyi.example.order.factory.OrderHandlerChain;
import com.luoyi.example.order.factory.OrderHandlerFactory;
import com.luoyi.example.order.vo.OrderResult;

/**
 * 模版抽象类
 */
public abstract class AbstractOrderService<T extends BaseOrderDTO> {

    // 核心模版方法（公共流程定义）
    public final OrderResult createOrder(T dto) {
        // Step1. 基础校验
        validateBase(dto);

        // Step2. 业务专属校验
        validateBusiness(dto);

        // Step3. 构造上下文
        OrderContext context = buildContext(dto);

        // Step4. 责任链处理核心逻辑
        executeHandlerChain(context);

        // Step5. 后置处理（可选钩子）
        return postProcess(context);
    }

    private void validateBase(T dto) {
        System.out.println("校验公共参数");
    }

    // 抽象方法：子类实现业务参数校验
    protected abstract void validateBusiness(T dto);

    // 抽象方法：构建订单上下文
    protected abstract OrderContext buildContext(T dto);

    // 责任链执行
    private void executeHandlerChain(OrderContext context) {
        OrderHandlerChain chain = OrderHandlerFactory.createChain(context.getOrderType());
        chain.execute(context);
    }

    // 后置处理钩子（默认实现，子类可选覆盖）
    protected OrderResult postProcess(OrderContext context) {
        return new OrderResult(context.getOrderId());
    }

    // 抽象方法：场景类型
    public abstract OrderType getOrderType();
}
