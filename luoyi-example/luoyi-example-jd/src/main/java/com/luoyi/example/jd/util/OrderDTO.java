package com.luoyi.example.jd.util;

import lombok.Data;

import java.util.List;

/**
 * @describe:
 * @author: chenlong
 * @date: 2024/12/26 15:37
 */
@Data
public class OrderDTO {
    /**
     * 平台主订单号
     */
    private String orderNumber;
    /**
     * 订单金额,分
     */
    private Long amount;
    /**
     * 订单描述
     */
    private String description;
    /**
     * 回调地址
     */
    private String callBackUrl;
    /**
     * 订单超时支付时间,时间戳精确到秒
     */
    private Integer expirationTime;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 商户号
     */
    private String merchantNo;
    /**
     * 终端号
     */
    private String terminalNo;
    /**
     * 选品集信息
     */
    private List<ProductSetCodeDTO> productSetCodes;
    /**
     * 限制使用支付类型 为空标识不限制
     */
    private List<Integer> restrictPaymentType;
    /**
     * 支付流水号,以SC_开头,长度30位以内
     */
    private String serialNumber;
    /**
     * 2:供应链线上订单  3: 卡券线上订单
     */
    private Integer businessType;
}
