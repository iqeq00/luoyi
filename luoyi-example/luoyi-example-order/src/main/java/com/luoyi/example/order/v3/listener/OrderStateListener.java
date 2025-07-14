//package com.luoyi.example.order.v3.listener;
//
//import com.luoyi.example.order.v3.entity.Order;
//import com.luoyi.example.order.v3.enums.OrderEvent;
//import com.luoyi.example.order.v3.enums.OrderStatus;
//import org.springframework.messaging.Message;
//import org.springframework.statemachine.annotation.OnTransition;
//import org.springframework.statemachine.annotation.WithStateMachine;
//import org.springframework.stereotype.Component;
//
//@Component
//@WithStateMachine(name = "orderStateMachine")
//public class OrderStateListener {
//
//    @OnTransition(source = "CREATED", target = "WAIT_PAYMENT")
//    public boolean submit(Message<OrderEvent> message) {
//        System.out.println("进来了");
//        Order order = (Order) message.getHeaders().get("order");
//        System.out.println("订单id:" + order.getId());
//        order.setStatus(OrderStatus.PAID.getEnDesc());
//        System.out.println("提交成功，订单状态变更为已提交");
//        return true;
//    }
//
//    @OnTransition(source = "WAIT_PAYMENT", target = "PAID")
//    public boolean payTransition(Message<OrderEvent> message) {
//        System.out.println("进来了");
//        Order order = (Order) message.getHeaders().get("order");
//        System.out.println("订单id:" + order.getId());
//        order.setStatus(OrderStatus.PAID.getEnDesc());
//        System.out.println("支付成功，订单状态变更为已支付");
//        return true;
//    }
//
//    @OnTransition(source = "PAID", target = "SHIPPED")
//    public boolean deliverTransition(Message<OrderEvent> message) {
//        Order order = (Order) message.getHeaders().get("order");
//        System.out.println("订单id:" + order.getId());
//        order.setStatus(OrderStatus.SHIPPED.getEnDesc());
//        System.out.println("发货成功，订单状态变更为待收货");
//        return true;
//    }
//
//}