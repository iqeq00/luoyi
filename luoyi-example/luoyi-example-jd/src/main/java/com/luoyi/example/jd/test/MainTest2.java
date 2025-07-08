package com.luoyi.example.jd.test;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

public class MainTest2 {

    public static void main(String[] args) {
//        IntStream.range(0, 10).forEach(i -> {
//            System.out.println(i);
//        });

//        // 示例：接收的日期参数
//        LocalDate endTime = LocalDate.of(2025, 6, 30);
//
//        // 使用 Hutool 转换为当天的开始时间
//        LocalDateTime startTime = LocalDateTimeUtil.beginOfDay(endTime);
//
//        // 使用 Hutool 转换为当天的结束时间
//        LocalDateTime endOfDayTime = LocalDateTimeUtil.endOfDay(endTime);
//
//
//        LocalDateTime localDateTime = LocalDateTimeUtil.endOfDay(endTime.atStartOfDay());
//
//        System.out.println("老版本：" + localDateTime);
//
//        // 输出结果
//        System.out.println("开始时间: " + startTime);
//        System.out.println("结束时间: " + endOfDayTime);

        Snowflake skuSnowflake = IdUtil.getSnowflake(1, 4);
        System.out.println(skuSnowflake.nextIdStr());

    }
}
