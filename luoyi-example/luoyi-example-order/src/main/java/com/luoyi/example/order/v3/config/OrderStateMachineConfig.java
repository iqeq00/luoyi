package com.luoyi.example.order.v3.config;

import com.luoyi.example.order.v3.enums.OrderEvent;
import com.luoyi.example.order.v3.enums.OrderStatus;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
//@EnableStateMachine(name = "orderStateMachine")
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderStatus, OrderEvent> {

    // 配置状态机初始状态和所有可能状态
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderEvent> states) throws Exception {
        states
                .withStates()
                // 初始状态：已创建
                .initial(OrderStatus.CREATED)
                // 所有状态集合（从枚举中获取）
                .states(EnumSet.allOf(OrderStatus.class));
    }

    // 配置状态流转规则（基于带描述的枚举）
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderEvent> transitions) throws Exception {
        transitions
                // 已创建 -> 待支付（提交订单事件）
                .withExternal()
                .source(OrderStatus.CREATED).target(OrderStatus.WAIT_PAYMENT)
                .event(OrderEvent.SUBMIT)
                .and()
                // 待支付 -> 已支付（支付事件）
                .withExternal()
                .source(OrderStatus.WAIT_PAYMENT).target(OrderStatus.PAID)
                .event(OrderEvent.PAY)
                .and()
                // 已支付 -> 已发货（发货事件）
                .withExternal()
                .source(OrderStatus.PAID).target(OrderStatus.SHIPPED)
                .event(OrderEvent.SHIP)
                .and()
                // 已发货 -> 已送达（送达事件）
                .withExternal()
                .source(OrderStatus.SHIPPED).target(OrderStatus.DELIVERED)
                .event(OrderEvent.DELIVER)
                .and()
                // 已创建 -> 已取消（取消事件）
                .withExternal()
                .source(OrderStatus.CREATED).target(OrderStatus.CANCELLED)
                .event(OrderEvent.CANCEL)
                .and()
                // 待支付 -> 已取消（取消事件）
                .withExternal()
                .source(OrderStatus.WAIT_PAYMENT).target(OrderStatus.CANCELLED)
                .event(OrderEvent.CANCEL)
                .and()
                // 已支付 -> 已退款（退款事件）
                .withExternal()
                .source(OrderStatus.PAID).target(OrderStatus.REFUNDED)
                .event(OrderEvent.REFUND);
    }

    // 配置状态机监听器（修复状态描述获取方式）
    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderStatus, OrderEvent> config) throws Exception {
        config
                .withConfiguration()
                .listener(new StateMachineListenerAdapter<OrderStatus, OrderEvent>() {
                    @Override
                    public void transition(Transition<OrderStatus, OrderEvent> transition) {
                        if (transition.getSource() != null && transition.getTarget() != null) {
                            // 通过 State.getId() 获取枚举值，再访问枚举属性
                            OrderStatus sourceStatus = transition.getSource().getId();
                            OrderStatus targetStatus = transition.getTarget().getId();
                            OrderEvent event = transition.getTrigger().getEvent();

                            System.out.printf(
                                    "订单状态变更: %s(%s) -> %s(%s)，触发事件: %s(%s)%n",
                                    sourceStatus.getEnDesc(),
                                    sourceStatus.getCnDesc(),
                                    targetStatus.getEnDesc(),
                                    targetStatus.getCnDesc(),
                                    event.getEnDesc(),
                                    event.getCnDesc()
                            );
                        }
                    }
                });
    }
}
