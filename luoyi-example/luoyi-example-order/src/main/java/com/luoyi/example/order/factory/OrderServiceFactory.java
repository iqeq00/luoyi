package com.luoyi.example.order.factory;

import com.luoyi.example.order.service.AbstractOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 订单场景工厂
 *
 * @author yaojinchi
 */
@Slf4j
@Component
public class OrderServiceFactory {

    @Autowired
    private List<AbstractOrderService> orderServices;

    public AbstractOrderService getInstance(String sceneType) {

        return orderServices.stream()
                .filter(o -> sceneType.equals(o.getSceneType()))
                .findFirst()
                .orElse(null);
//                .orElseThrow(() -> new Exception("没有获取到订单模版，请返回检查"));
    }

}