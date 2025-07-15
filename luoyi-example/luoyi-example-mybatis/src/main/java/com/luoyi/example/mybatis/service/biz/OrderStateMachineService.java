package com.luoyi.example.mybatis.service.biz;

import com.luoyi.example.mybatis.enums.OrderEvent;
import com.luoyi.example.mybatis.enums.OrderState;
import com.luoyi.example.mybatis.listener.OrderStateChangeListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderStateMachineService {

    private final StateMachineFactory<OrderState, OrderEvent> factory;
    private final StateMachinePersister<OrderState, OrderEvent, String> persister;
    private final OrderStateChangeListener listener;

    public StateMachine<OrderState, OrderEvent> acquire(String orderId) {
        StateMachine<OrderState, OrderEvent> sm = factory.getStateMachine(orderId);
        try {
            persister.restore(sm, orderId);
        } catch (Exception e) {
            log.error("恢复状态机失败, orderId={}", orderId, e);
        }
        sm.start(); // 同步启动
        if (!sm.getExtendedState().getVariables().containsKey("listenerRegistered")) {
            sm.addStateListener(listener);
            sm.getExtendedState().getVariables().put("orderId", orderId);
            sm.getExtendedState().getVariables().put("listenerRegistered", true);
        }
        return sm;
    }

    public boolean sendEvent(String orderId, OrderEvent event) {
        StateMachine<OrderState, OrderEvent> sm = acquire(orderId);
        Message<OrderEvent> msg = MessageBuilder.withPayload(event).build();
        boolean accepted = sm.sendEvent(Mono.just(msg)).blockLast() != null;
        try {
            persister.persist(sm, orderId);
        } catch (Exception e) {
            log.error("持久化状态机失败, orderId={}", orderId, e);
        }
        return accepted;
    }
}