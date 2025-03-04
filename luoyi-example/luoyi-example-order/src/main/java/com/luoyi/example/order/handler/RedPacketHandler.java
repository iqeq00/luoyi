package com.luoyi.example.order.handler;

import com.alibaba.fastjson2.annotation.JSONType;
import com.luoyi.example.order.context.OrderContext;
import com.luoyi.example.order.enums.SceneTypeEnum;
import com.luoyi.example.order.json.ClassNameSerializer;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Set;

/**
 * 红包处理
 */
@Service
@JSONType(serializer = ClassNameSerializer.class)
public class RedPacketHandler implements OrderHandler {

    // 红包服务

    @Override
    public void handle(OrderContext context) {
        System.out.println("查询红包");
//        throw new RuntimeException("红包查询出错");
    }

    @Override
    public Integer getSorted() {
        return 0;
    }


    @Override
    public Set<SceneTypeEnum> supportedSceneTypes() {
        return EnumSet.allOf(SceneTypeEnum.class);
    }

}
