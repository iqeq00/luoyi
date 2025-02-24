package com.luoyi.example.jd.util;

import lombok.Data;

/**
 * @describe: TODO
 * @author: chenlong
 * @date: 2024/12/26 15:36
 */
@Data
public class CreatePayOrderDTO {
    /**
     * 签名
     */
    private String sign;
    /**
     * 应用编号
     */
    private String appCode;
    /**
     * 订单信息
     */
    private OrderDTO data;
}
