package com.luoyi.example.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单场景大类
 * 一般情况下ENTITY为实物订单 有子订单
 * SERVICE为服务订单 无子订单
 *
 */
@AllArgsConstructor
@Getter
public enum PrimacySceneTypeEnum implements IBaseEnum<String> {

    ENTITY("ENTITY","实物订单"),
    H5MALL("H5MALL","H5订单"),
    SERVICE("SERVICE","服务订单"),
    ;


    private final String value;
    private final String label;
}
