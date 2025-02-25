package com.luoyi.example.order.service;

import com.luoyi.example.order.context.OrderContext;
import com.luoyi.example.order.dto.EntityOrderDTO;
import com.luoyi.example.order.enums.OrderType;
import com.luoyi.example.order.vo.OrderResult;
import org.springframework.stereotype.Service;

@Service
public class EntityOrderService extends AbstractOrderService<EntityOrderDTO> {

    @Override
    protected void validateBusiness(EntityOrderDTO dto) {
        System.out.println("实物参数校验");
    }

    @Override
    protected OrderContext buildContext(EntityOrderDTO dto) {

        OrderContext orderContext = new OrderContext();
        orderContext.setOrderType(dto.getOrderType());
        System.out.println("实物执行上下文构建");
        return orderContext;
    }

    @Override
    protected OrderResult postProcess(OrderContext context) {
        return new OrderResult(context.getOrderId());
    }

    @Override
    public OrderType getOrderType() {
        return OrderType.ENTITY;
    }
}
