package com.luoyi.example.order.handler;

import com.luoyi.example.order.context.OrderContext;

import java.util.List;

/**
 * 订单处理器责任链
 *
 * @author yaojinchi
 */
public class OrderHandlerChain {

    private final List<OrderHandler> handlers;

    private int position = 0;

    public OrderHandlerChain(List<OrderHandler> handlers) {
        this.handlers = handlers;
    }

    /**
     * 根据订单上下文执行
     */
    public void execute(OrderContext context) {

        if (position < handlers.size()) {
            OrderHandler handler = handlers.get(position++);
            handler.handle(context);
            execute(context);
        }
    }

}