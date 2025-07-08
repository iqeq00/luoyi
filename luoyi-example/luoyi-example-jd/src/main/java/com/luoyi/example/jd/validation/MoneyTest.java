package com.luoyi.example.jd.validation;

import cn.hutool.core.util.NumberUtil;

import java.math.BigDecimal;

public class MoneyTest {

    public static void main(String[] args) {
        System.out.println(convertToCents1("1000.00"));
        System.out.println(convertToCents1("1000"));
    }

    private static long convertToCents1(String amountStr) {
        if (amountStr == null || amountStr.isEmpty()) {
            return 0L;
        }
        // 解析为 BigDecimal 并乘以100
        return NumberUtil.mul(amountStr, "100").longValueExact();
    }

    // 转换方法：将字符串金额（元）转换为 long（分）
    private static long convertToCents(String amountStr) {
        if (amountStr == null || amountStr.isEmpty()) {
            return 0L;
        }

        // 使用 BigDecimal 处理精确小数计算
        BigDecimal amount = new BigDecimal(amountStr);
        BigDecimal cents = amount.multiply(new BigDecimal("100"));

        // 转换为 long，丢弃小数部分（类似向下取整）
        // 如果你需要四舍五入，可以使用 .setScale(0, RoundingMode.HALF_UP)
        return cents.longValueExact();
    }
}
