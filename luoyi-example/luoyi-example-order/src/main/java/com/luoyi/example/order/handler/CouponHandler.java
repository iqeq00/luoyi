package com.luoyi.example.order.handler;

import com.alibaba.fastjson2.annotation.JSONType;
import com.luoyi.example.order.context.OrderContext;
import com.luoyi.example.order.enums.SceneTypeEnum;
import com.luoyi.example.order.json.ClassNameSerializer;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Set;

/**
 * 优惠券处理
 */
@Service
@JSONType(serializer = ClassNameSerializer.class)
public class CouponHandler implements OrderHandler {

    // 优惠券服务

    @Override
    public void handle(OrderContext context) {
        System.out.println("查询优惠券，");
    }

    @Override
    public Integer getSorted() {
        return 0;
    }

    @Override
    public Set<SceneTypeEnum> supportedSceneTypes() {
        return EnumSet.of(SceneTypeEnum.ENTITY);
    }

}