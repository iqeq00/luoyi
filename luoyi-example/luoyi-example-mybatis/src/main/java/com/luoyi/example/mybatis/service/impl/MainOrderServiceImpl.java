package com.luoyi.example.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luoyi.example.mybatis.enums.OrderState;
import com.luoyi.example.mybatis.mapper.MainOrderMapper;
import com.luoyi.example.mybatis.model.entity.MainOrder;
import com.luoyi.example.mybatis.service.MainOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MainOrderServiceImpl extends ServiceImpl<MainOrderMapper, MainOrder> implements MainOrderService {

    public void handleStateChanged(String orderId, OrderState newState) {
        log.info("【业务逻辑】订单 {} 新状态: {}", orderId, newState.getDesc());
        // 这里写真正的业务：更新订单表、发消息、记日志……
    }
}
