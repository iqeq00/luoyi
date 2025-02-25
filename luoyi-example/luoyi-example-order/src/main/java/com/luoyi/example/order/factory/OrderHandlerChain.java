package com.luoyi.example.order.factory;

import com.luoyi.example.order.context.OrderContext;
import com.luoyi.example.order.handler.OrderHandler;

import java.util.List;

public class OrderHandlerChain {

    private final List<OrderHandler> handlers;
    private int position = 0;

    public OrderHandlerChain(List<OrderHandler> handlers) {
        this.handlers = handlers;
    }

    public void execute(OrderContext context) {
        if (position < handlers.size()) {
            OrderHandler handler = handlers.get(position++);
            handler.handle(context);
            execute(context); // 递归调用下一个处理器
        }
    }
}
