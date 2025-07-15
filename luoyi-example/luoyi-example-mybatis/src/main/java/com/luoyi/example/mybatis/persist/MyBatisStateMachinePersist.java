package com.luoyi.example.mybatis.persist;

import com.luoyi.example.mybatis.enums.OrderEvent;
import com.luoyi.example.mybatis.enums.OrderState;
import com.luoyi.example.mybatis.mapper.StateMachineMapper;
import com.luoyi.example.mybatis.model.entity.StateMachine;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.stereotype.Component;

import java.io.*;


@Component
@RequiredArgsConstructor
public class MyBatisStateMachinePersist
        implements StateMachinePersist<OrderState, OrderEvent, String> {

    private final StateMachineMapper mapper;

    @Override
    public void write(StateMachineContext<OrderState, OrderEvent> context, String orderId) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(context);
        }
        StateMachine entity = new StateMachine();
        entity.setMachineId(orderId);
        entity.setState(context.getState().name());
        entity.setStateMachineContext(bos.toByteArray());
        mapper.insertOrUpdate(entity);
    }

    @Override
    public StateMachineContext<OrderState, OrderEvent> read(String orderId) throws Exception {
        StateMachine entity = mapper.selectById(orderId);
        if (entity == null || entity.getStateMachineContext() == null) return null;
        try (ObjectInputStream ois =
                     new ObjectInputStream(new ByteArrayInputStream(entity.getStateMachineContext()))) {
            return (StateMachineContext<OrderState, OrderEvent>) ois.readObject();
        }
    }
}
