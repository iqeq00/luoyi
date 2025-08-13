package com.luoyi.example.order.collection;

import lombok.Data;

import java.io.Serializable;

/**
 * 条件测试对象1
 *
 * @author yaojinchi
 */
@Data
public class ConditionEntity1 implements Serializable {

    /**
     * 供应商code
     */
    private String supplierCodes;

    /**
     * 平台类目code
     */
    private String platformCategoryCodes;

    /**
     * 利率
     */
    private String profitRate;

    /**
     * 标准销售价
     */
    private Long standardSalePrice;

}