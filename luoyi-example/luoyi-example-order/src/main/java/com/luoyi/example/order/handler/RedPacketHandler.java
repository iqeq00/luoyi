package com.luoyi.example.order.handler;

import com.luoyi.example.order.context.OrderContext;
import com.luoyi.example.order.dto.BaseOrderDTO;
import com.luoyi.example.order.enums.OrderType;
import com.luoyi.example.order.enums.SceneTypeEnum;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Set;

/**
 * 红包处理
 */
@Service
public class RedPacketHandler implements OrderHandler {

    // 红包服务

    @Override
    public void handle(OrderContext context) {
        System.out.println("查询红包");
//        throw new RuntimeException("红包查询出错");
    }

    @Override
    public int getOrder() {
        return 11;
    }

    @Override
    public Set<SceneTypeEnum> supportedTypes() {
        return EnumSet.allOf(SceneTypeEnum.class);
    }
}
