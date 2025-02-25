package com.luoyi.example.order.handler;

import com.luoyi.example.order.context.OrderContext;
import com.luoyi.example.order.enums.OrderType;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Set;

/**
 * 优惠券处理
 */
@Service
public class CouponHandler implements OrderHandler {

    // 优惠券服务

    @Override
    public void handle(OrderContext context) {
        System.out.println("查询优惠券，");
    }

    @Override
    public int getOrder() {
        return 10;
    }

    @Override
    public Set<OrderType> supportedTypes() {
        return EnumSet.of(OrderType.ENTITY);
    }
}
