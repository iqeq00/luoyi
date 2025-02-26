package com.luoyi.example.order.controller;

import com.luoyi.example.order.dto.BaseOrderDTO;
import com.luoyi.example.order.facade.OrderFacade;
import com.luoyi.example.order.vo.CreateOrderResVO;
import com.luoyi.example.order.vo.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单服务
 */
@RestController
public class OrderController {

    @Autowired
    public OrderFacade orderFacade;

    /**
     * 统一下单接口
     */
    @PostMapping("/order")
    public Result<CreateOrderResVO> createOrder(@Valid @RequestBody BaseOrderDTO dto) {

        return orderFacade.createOrder(dto);
    }

}