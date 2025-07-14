package com.luoyi.example.order.v3.service;

import com.luoyi.example.order.v3.entity.Order;
import com.luoyi.example.order.v3.enums.OrderEvent;
import com.luoyi.example.order.v3.enums.OrderStatus;
import com.luoyi.example.order.v3.listener.OrderStateListener1;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderStateMachineService {

//    @Autowired
//    private StateMachine<OrderStatus, OrderEvent> orderStateMachine;

    @Autowired
    private final OrderStateListener1 orderStateListener;

    @Autowired
    private StateMachineFactory<OrderStatus, OrderEvent> stateMachineFactory;

    public boolean sendEvent(Order order, OrderEvent event) {

        // 创建独立的状态机实例
        StateMachine<OrderStatus, OrderEvent> orderStateMachine = buildStateMachine(order.getId());
        orderStateMachine.addStateListener(orderStateListener.createListener(order.getId().toString()));

        Message<OrderEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader("order", order)
                .build();

        return orderStateMachine.sendEvent(message);
    }

    private StateMachine<OrderStatus, OrderEvent> buildStateMachine(Long orderId) {
        StateMachine<OrderStatus, OrderEvent> stateMachine = stateMachineFactory.getStateMachine(String.valueOf(orderId));
        stateMachine.start();
        return stateMachine;
    }
}