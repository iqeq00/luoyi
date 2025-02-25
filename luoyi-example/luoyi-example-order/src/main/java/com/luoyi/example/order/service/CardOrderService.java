package com.luoyi.example.order.service;

import com.luoyi.example.order.context.OrderContext;
import com.luoyi.example.order.dto.CardOrderDTO;
import com.luoyi.example.order.enums.OrderType;
import org.springframework.stereotype.Service;

@Service
public class CardOrderService extends AbstractOrderService<CardOrderDTO> {

    @Override
    protected void validateBusiness(CardOrderDTO dto) {
        System.out.println("卡券参数校验");
    }

    @Override
    protected OrderContext buildContext(CardOrderDTO dto) {
        OrderContext orderContext = new OrderContext();
        orderContext.setOrderType(dto.getOrderType());
        System.out.println("卡券执行上下文构建");
        return orderContext;
    }

    @Override
    public OrderType getOrderType() {
        return OrderType.CARD;
    }
}
