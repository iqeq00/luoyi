package com.luoyi.example.order.service;

import com.alibaba.fastjson2.JSON;
import com.luoyi.example.order.context.OrderContext;
import com.luoyi.example.order.dto.BaseOrderDTO;
import com.luoyi.example.order.enums.SceneTypeEnum;
import com.luoyi.example.order.factory.OrderHandlerChain;
import com.luoyi.example.order.factory.OrderHandlerFactory;
import com.luoyi.example.order.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 模版抽象类
 */
@Slf4j
public abstract class AbstractOrderService<T extends BaseOrderDTO> {

    @Autowired
    public OrderHandlerFactory orderHandlerFactory;

    // 核心模版方法（公共流程定义）
    public final Result createOrder(T dto) {
        // Step1. 基础校验
        validateBase(dto);

        // Step2. 业务专属校验
        validateBusiness(dto);

        // Step3. 构造上下文
        OrderContext context = buildContext(dto);
        context.setSceneType(SceneTypeEnum.getInstance(dto.getSceneType()));

//        // Step4. 构建业务参数
//        buildBusinessParams(context);

        // Step5. 责任链处理核心逻辑
        executeHandlerChain(context);

        // Step6. 后置处理（可选钩子）
        return postProcess(context);
    }

    private void validateBase(T dto) {
        System.out.println("校验公共参数");
        log.info("入参：{}", JSON.toJSONString(dto));
    }

    // 抽象方法：子类实现业务参数校验
    protected abstract void validateBusiness(T dto);

    // 抽象方法：构建订单上下文
    protected abstract OrderContext buildContext(T dto);

//    // 抽象方法：构建业务参数
//    protected abstract void buildBusinessParams(OrderContext context);

    // 责任链执行
    private void executeHandlerChain(OrderContext context) {
        OrderHandlerChain chain = orderHandlerFactory.createChain(context.getSceneType());
        chain.execute(context);
    }

    // 后置处理钩子（默认实现，子类可选覆盖）
    protected Result postProcess(OrderContext context) {
        return Result.success(context.getOrderId());
    }

    // 抽象方法：场景类型
    public abstract String getSceneType();

}