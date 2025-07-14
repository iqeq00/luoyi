package com.luoyi.example.order.v3.entity;

import com.luoyi.example.order.v3.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Order {

    private Long id;

    private String orderNumber;

    private String status;

    private BigDecimal amount;

    private String userId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

//    // 订单状态变更方法
//    public void updateStatus(OrderStatus newStatus) {
//        this.status = newStatus;
//        this.updateTime = LocalDateTime.now();
//    }
}
