package com.luoyi.example.order.factory;

import com.luoyi.example.order.enums.OrderType;
import com.luoyi.example.order.service.AbstractOrderService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderServiceFactory {

    private List<AbstractOrderService> orderServices;

    public AbstractOrderService getInstance(OrderType orderType) {
        return orderServices.stream()
                .filter(o -> orderType.equals(o.getOrderType()))
                .findFirst()
                .orElse(null);
//                .orElseThrow(() -> new Exception("没有获取到订单模版，请返回检查"));
    }
}
