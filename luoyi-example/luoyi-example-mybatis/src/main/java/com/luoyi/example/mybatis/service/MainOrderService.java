package com.luoyi.example.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luoyi.example.mybatis.enums.OrderState;
import com.luoyi.example.mybatis.model.entity.MainOrder;

public interface MainOrderService extends IService<MainOrder> {

    void handleStateChanged(String orderId, OrderState newState);

}