//package com.luoyi.example.order.v3.service;
//
//import com.luoyi.example.order.v3.entity.Order;
//import com.luoyi.example.order.v3.enums.OrderEvent;
//import com.luoyi.example.order.v3.enums.OrderStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.statemachine.StateMachine;
//import org.springframework.statemachine.config.StateMachineFactory;
//import org.springframework.statemachine.persist.StateMachinePersister;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//
//@Service
//public class OrderService {
//
////    @Autowired
////    private OrderRepository orderRepository;
//
//    @Autowired
//    private StateMachineFactory<OrderStatus, OrderEvent> stateMachineFactory;
//
////    @Autowired
////    private StateMachinePersister<OrderStatus, OrderEvent, Order> persister;
//
//    // 创建新订单
//    @Transactional
//    public Order createOrder(String userId, BigDecimal amount) {
//        Order order = new Order();
//        order.setOrderNumber(generateOrderNumber());
//        order.setStatus(OrderStatus.CREATED.getEnDesc());
//        order.setUserId(userId);
//        order.setAmount(amount);
//        order.setCreateTime(java.time.LocalDateTime.now());
//        order.setUpdateTime(java.time.LocalDateTime.now());
//
////        return orderRepository.save(order);
//        System.out.println("保存成功");
//        return order;
//    }
//
//    // 执行订单状态转换
//    @Transactional
//    public boolean processOrderEvent(Long orderId, OrderEvent event) {
////        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("订单不存在"));
//
//        // 查询订单
//        Order order = new Order();
//
//        StateMachine<OrderStatus, OrderEvent> stateMachine = stateMachineFactory.getStateMachine();
//
//        try {
//            // 恢复状态机状态
////            persister.restore(stateMachine, order);
//
//            // 执行事件触发状态转换
//            stateMachine.sendEvent(event);
//
//            // 保存新状态
////            order.updateStatus(stateMachine.getState().getId());
////            orderRepository.save(order);
//            System.out.println("更新订单新状态");
//
////            // 持久化状态机
////            persister.persist(stateMachine, order);
//
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    // 生成订单号
//    private String generateOrderNumber() {
//        // 简化实现，实际应使用UUID或分布式ID生成器
//        return "ORD" + System.currentTimeMillis();
//    }
//}
