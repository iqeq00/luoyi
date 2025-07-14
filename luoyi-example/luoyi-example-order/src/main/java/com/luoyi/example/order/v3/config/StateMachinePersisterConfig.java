//package com.luoyi.example.order.v3.config;
//
//import com.luoyi.example.order.v3.entity.Order;
//import com.luoyi.example.order.v3.enums.OrderEvent;
//import com.luoyi.example.order.v3.enums.OrderStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.statemachine.persist.DefaultStateMachinePersister;
//import org.springframework.statemachine.persist.StateMachinePersister;
//
//@Configuration
//public class StateMachinePersisterConfig {
//
//    // 配置状态机持久化策略
//    @Bean
//    public StateMachinePersister<OrderStatus, OrderEvent, Order> persister() {
//        return new DefaultStateMachinePersister<>(
//                // 这里可以自定义状态机持久化实现
//                // 示例使用内存存储，实际生产环境建议使用Redis或数据库
//                (context, order) -> {
//                    // 保存状态机上下文到订单对象
//                    order.setStatus(context.getState());
//                    return null;
//                },
//                (order) -> {
//                    // 从订单对象恢复状态机上下文
//                    return null; // 简化示例，实际需实现
//                }
//        );
//    }
//}
