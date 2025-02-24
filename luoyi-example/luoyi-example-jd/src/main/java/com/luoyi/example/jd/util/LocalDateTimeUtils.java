package com.luoyi.example.jd.util;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.NumberUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

/**
 * 日期工具类
 */
public class LocalDateTimeUtils {

    /**
     * 获取当前时间
     */
    public static LocalDateTime now() {

        return LocalDateTime.now();
    }

    /**
     * 获取当天开始时间
     */
    public static LocalDateTime beginOfDay() {

        return LocalDateTimeUtil.beginOfDay(now());
    }

    /**
     * 获取当天结束时间
     */
    public static LocalDateTime endOfDay() {

        return LocalDateTimeUtil.endOfDay(now());
    }

    /**
     * 获取当月开始时间
     */
    public static LocalDateTime beginOfMonth() {

        return now().toLocalDate().with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
    }

    /**
     * 获取当月结束时间
     */
    public static LocalDateTime endOfMonth() {

        return now().toLocalDate().with(TemporalAdjusters.lastDayOfMonth()).atTime(LocalTime.MAX);
    }


    public static void main(String[] args) {
        System.out.println(beginOfDay());
        System.out.println(endOfDay());
        System.out.println(beginOfMonth());
        System.out.println(endOfMonth());


        System.out.println(NumberUtil.compare(10L, 10L));
    }

}
