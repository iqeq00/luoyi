package com.luoyi.example.order.json;

import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.writer.ObjectWriter;
import com.luoyi.example.order.handler.OrderHandler;

import java.lang.reflect.Type;

/**
 * fastjson2 类名序列化器
 * 直接写入类名
 *
 * @author yaojinchi
 */
public class ClassNameSerializer implements ObjectWriter<OrderHandler> {

    @Override
    public void write(JSONWriter writer, Object handler, Object fieldName, Type fieldType, long features) {

        writer.writeString(handler.getClass().getSimpleName());
    }

}