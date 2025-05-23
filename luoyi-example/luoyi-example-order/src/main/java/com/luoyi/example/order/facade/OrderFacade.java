package com.luoyi.example.order.facade;

import com.luoyi.example.order.dto.BaseOrderDTO;
import com.luoyi.example.order.factory.OrderServiceFactory;
import com.luoyi.example.order.vo.CreateOrderResVO;
import com.luoyi.example.order.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderFacade {

    @Autowired
    public OrderServiceFactory orderServiceFactory;

    /**
     * 下订单
     */
    // 加锁
    public Result<CreateOrderResVO> createOrder(BaseOrderDTO dto) {

        return orderServiceFactory.getInstance(dto.getSceneType()).createOrder(dto);
    }

}