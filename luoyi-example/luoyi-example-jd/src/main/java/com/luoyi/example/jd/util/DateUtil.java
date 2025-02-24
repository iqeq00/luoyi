package com.luoyi.example.jd.util;

import cn.hutool.core.date.LocalDateTimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

public class DateUtil {

    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime beginOfDay = LocalDateTimeUtil.beginOfDay(now);
        LocalDateTime endOfDay = LocalDateTimeUtil.endOfDay(now);
        System.out.println(beginOfDay);
        System.out.println(endOfDay);

        LocalDate firstDayOfMonth = now.toLocalDate().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfMonth = now.toLocalDate().with(TemporalAdjusters.lastDayOfMonth());

        LocalDateTime firstDayOfMonthDateTime = firstDayOfMonth.atStartOfDay();
        LocalDateTime lastDayOfMonthDateTime = lastDayOfMonth.atTime(LocalTime.MAX); // 当天的最后一刻，即23:59:59.999999999

        System.out.println("First day of month: " + firstDayOfMonthDateTime);
        System.out.println("Last day of month: " + lastDayOfMonthDateTime);
    }
}
