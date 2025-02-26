package com.luoyi.example.order.service;

import com.luoyi.example.order.context.OrderContext;
import com.luoyi.example.order.dto.EntityOrderDTO;
import com.luoyi.example.order.enums.SceneTypeEnum;
import com.luoyi.example.order.vo.Result;
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
        System.out.println("实物执行上下文构建");
        return orderContext;
    }

    @Override
    protected Result postProcess(OrderContext context) {
        return Result.success(context.getOrderId());
    }

    @Override
    public String getSceneType() {
        return SceneTypeEnum.ENTITY.getValue();
    }
}
