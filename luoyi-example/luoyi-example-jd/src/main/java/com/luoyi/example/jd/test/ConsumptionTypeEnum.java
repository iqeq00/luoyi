package com.luoyi.example.jd.test;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @describe: 优惠券类型
 * @author: chenlong
 * @date: 2025/3/3 13:45
 */

@AllArgsConstructor
@Getter
public enum ConsumptionTypeEnum {

    REDUCTION("满减"),
    DISCOUNT("折扣")
    ;

    private String label;
}
