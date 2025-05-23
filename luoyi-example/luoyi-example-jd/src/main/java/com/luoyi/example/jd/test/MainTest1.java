package com.luoyi.example.jd.test;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MainTest1 {

    public static void main(String[] args) {

//        BigDecimal amount = new BigDecimal("0.8").multiply(new BigDecimal(String.valueOf(1000)));
//        Long discountAmount = amount.setScale(0, BigDecimal.ROUND_DOWN).longValue();
//        System.out.println(discountAmount);
//        System.out.println(ConsumptionTypeEnum.DISCOUNT.name());
//        String date = "2025-04-08 00:00:00";
//        LocalDateTime parse = LocalDateTimeUtil.parse(date);
//        System.out.println(parse);


        DateTime dt = DateUtil.parse("2025-04-08 00:00:00");
        LocalDateTime parse = dt.toLocalDateTime();
        System.out.println(parse);

    }

}
