package com.luoyi.example.order.context;

import java.util.List;

// 定义类型安全的Attribute容器类
public final class AttributeKey<T> {
    private final String name;

    public AttributeKey(String name) {
        this.name = name;
    }

    // 建议将常用Key集中管理
    public static class Keys {
        public static final AttributeKey<String> SKU_ID = new AttributeKey<>("skuId");
        public static final AttributeKey<Integer> QUANTITY = new AttributeKey<>("quantity");
        public static final AttributeKey<List<String>> SEATS = new AttributeKey<>("seats");
        public static final AttributeKey<String> CINEMA_ID = new AttributeKey<>("cinemaId");
        // 后续新增属性可在此追加
    }
}
