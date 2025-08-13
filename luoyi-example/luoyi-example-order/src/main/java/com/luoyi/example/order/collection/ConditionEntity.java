package com.luoyi.example.order.collection;

import lombok.Data;

import java.io.Serializable;

/**
 * 条件测试对象
 *
 * @author yaojinchi
 */
@Data
public class ConditionEntity implements Serializable {

    /**
     * 平台品牌id
     */
    private String platformBrandIds;

    /**
     * 平台类目code
     */
    private String platformCategoryCodes;

    /**
     * 结算价
     */
    private Long chainSettlePrice;

    /**
     * 标准销售价
     */
    private Long standardSalePrice;

    /**
     * 利率
     */
    private String profitRate;

}