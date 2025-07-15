package com.luoyi.example.mybatis.config;

import com.luoyi.example.mybatis.enums.OrderEvent;
import com.luoyi.example.mybatis.enums.OrderState;
import com.luoyi.example.mybatis.persist.MyBatisStateMachinePersist;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory(name = "orderStateMachineFactory")
@RequiredArgsConstructor
public class StateMachineConfig
        extends StateMachineConfigurerAdapter<OrderState, OrderEvent> {

    private final MyBatisStateMachinePersist persist;

    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
        states.withStates()
                .initial(OrderState.INIT)
                .states(EnumSet.allOf(OrderState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
        transitions
                .withExternal().source(OrderState.INIT).target(OrderState.CREATE)
                .event(OrderEvent.CREATE_ORDER)
                .and()
                .withExternal().source(OrderState.CREATE).target(OrderState.PAID)
                .event(OrderEvent.PAY)
                .and()
                .withExternal().source(OrderState.CREATE).target(OrderState.CANCEL_PAY)
                .event(OrderEvent.CANCEL_PAY)
                .and()
                .withExternal().source(OrderState.INIT).target(OrderState.CANCEL_ORDER)
                .event(OrderEvent.CANCEL_ORDER)
                .and()
                .withExternal().source(OrderState.CREATE).target(OrderState.CANCEL_ORDER)
                .event(OrderEvent.CANCEL_ORDER)
                .and()
                .withExternal().source(OrderState.PAID).target(OrderState.COMPLETED)
                .event(OrderEvent.COMPLETE)
                .and()
                .withExternal().source(OrderState.CREATE).target(OrderState.REFUSAL)
                .event(OrderEvent.REFUSE);
    }

    @Bean
    public StateMachinePersister<OrderState, OrderEvent, String> stateMachinePersister() {
        return new DefaultStateMachinePersister<>(persist);
    }
}