package com.luoyi.example.mybatis.listener;

import com.luoyi.example.mybatis.enums.OrderEvent;
import com.luoyi.example.mybatis.enums.OrderState;
import com.luoyi.example.mybatis.service.MainOrderService;
import com.luoyi.example.mybatis.util.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderStateChangeListener extends StateMachineListenerAdapter<OrderState, OrderEvent> {

    @Override
    public void stateContext(StateContext<OrderState, OrderEvent> context) {
        if (context.getStage() != StateContext.Stage.STATE_CHANGED) return;

        String orderId = (String) context.getStateMachine()
                .getExtendedState()
                .getVariables()
                .get("orderId");
        OrderState fromState = context.getSource() != null ? context.getSource().getId() : null;
        OrderState toState   = context.getTarget() != null ? context.getTarget().getId() : null;

        log.info("订单[{}] 状态: {} -> {}", orderId,
                fromState == null ? null : fromState.getDesc(),
                toState   == null ? null : toState.getDesc());

        SpringContextHolder.getBean(MainOrderService.class).handleStateChanged(orderId, toState);
    }
}
