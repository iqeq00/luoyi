package com.luoyi.example.card;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Pair;

import java.util.Arrays;
import java.util.List;

public class MainTest {

    public static void main(String[] args) {
//        List<String> list = Arrays.asList("MY_4402", "TYZS_863362", "TYZS_863361", "ZGHG_5611");
//        System.out.println(CollectionUtil.contains(list, "TYZS_863361"));
        // 创建不可变Pair
        Pair<String, Integer> immutablePair = Pair.of("age", 25);
        System.out.println("Key: " + immutablePair.getKey());
        System.out.println("Value: " + immutablePair.getValue());

//        // 创建可变Pair
//        Pair<String, String> mutablePair = Pair.of("name", "张三");
//        mutablePair.setValue("李四"); // 可以修改值
//        System.out.println("修改后的值: " + mutablePair.getValue());

        // 作为方法返回值
        Pair<Boolean, String> result = doSomething();
        if (result.getKey()) {
            System.out.println("操作成功: " + result.getValue());
        } else {
            System.out.println("操作失败: " + result.getValue());
        }
    }

    // 方法返回两个相关结果
    private static Pair<Boolean, String> doSomething() {
        // 模拟业务逻辑
        boolean success = true;
        String message = "处理完成";
        return Pair.of(success, message);
    }
}
