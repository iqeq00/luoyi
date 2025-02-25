package com.luoyi.example.order.facade;

import com.luoyi.example.order.dto.BaseOrderDTO;
import com.luoyi.example.order.factory.OrderServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderFacade {

    @Autowired
    public OrderServiceFactory orderServiceFactory;

    public void createOrder(BaseOrderDTO dto) {
        orderServiceFactory.getInstance(dto.getOrderType()).createOrder(dto);
    }
}
