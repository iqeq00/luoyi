package com.luoyi.example.order.v3.listener;

import com.luoyi.example.order.v3.enums.OrderEvent;
import com.luoyi.example.order.v3.enums.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderStateListener1 {

//    private final OrderService orderService;
//
//    public OrderStateListener(OrderService orderService) {
//        this.orderService = orderService;
//    }

    /**
     * 为特定订单创建状态监听器
     */
    public StateMachineListenerAdapter<OrderStatus, OrderEvent> createListener(String orderNo) {
        return new StateMachineListenerAdapter<>() {
            @Override
            public void stateChanged(State<OrderStatus, OrderEvent> from, State<OrderStatus, OrderEvent> to) {
                if (to != null) {
                    log.info("订单[{}]状态变更: {} -> {}", orderNo,
                            from != null ? from.getId() : "NONE", to.getId());
                    // 调用Service层处理状态变更逻辑
//                    orderService.handleOrderStatusChange(orderNo, to.getId());
                }
            }

            @Override
            public void eventNotAccepted(Message<OrderEvent> event) {
                log.warn("订单[{}]事件不被接受: {}", orderNo, event.getPayload());
            }
        };
    }
}
