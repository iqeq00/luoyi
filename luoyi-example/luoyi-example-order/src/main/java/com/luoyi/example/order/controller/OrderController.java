package com.luoyi.example.order.controller;

import com.luoyi.example.order.v3.entity.Order;
import com.luoyi.example.order.v3.enums.OrderEvent;
//import com.luoyi.example.order.v3.service.OrderService;
import com.luoyi.example.order.v3.service.*;
import com.luoyi.example.order.v3.vo.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 订单服务
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

//    @Autowired
//    private OrderService orderService;

    @Autowired
    private OrderStateMachineService orderStateMachineService;

    @PostMapping("/submit")
    public String submit(@RequestBody OrderDTO orderDTO) {
//        Order order = orderService.getOrder(orderId);
        Order order = new Order();
        order.setId(orderDTO.getOrderId());
        boolean result = orderStateMachineService.sendEvent(order, OrderEvent.getInstance(orderDTO.getEvent()));
        return result ? "成功" : "失败，当前状态不允许支付";
    }

    @PostMapping("/pay")
    public String payOrder(@RequestBody OrderDTO orderDTO) {
//        Order order = orderService.getOrder(orderId);
        Order order = new Order();
        boolean result = orderStateMachineService.sendEvent(order, OrderEvent.PAY);
        return result ? "支付成功" : "支付失败，当前状态不允许支付";
    }

//    @PostMapping("/pay/{orderId}")
//    public String payOrder(@PathVariable Long orderId) {
//        Order order = orderStateMachineService.getOrder(orderId);
//        boolean result = orderStateMachineService.sendEvent(order, OrderStatusChangeEvent.PAYED);
//        return result ? "支付成功" : "支付失败，当前状态不允许支付";
//    }
//
//    // 处理订单事件
//    @PostMapping("/events")
//    public ResponseEntity<String> processOrderEvent(@RequestBody OrderDTO orderDTO) {
//        OrderEvent orderEvent = OrderEvent.getInstance(orderDTO.getEvent());
//        boolean result = orderService.processOrderEvent(orderDTO.getOrderId(), orderEvent);
//
//        if (result) {
//            return ResponseEntity.ok("订单状态已更新");
//        } else {
//            return ResponseEntity.badRequest().body("订单状态更新失败");
//        }
//    }
//
//    // 获取订单详情
//    @GetMapping("/{orderId}")
//    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
//        Order order = orderService.getOrderById(orderId);
//        return ResponseEntity.ok(order);
//    }
}