package com.luoyi.example.jd.util;

import lombok.Data;

import java.util.List;

/**
 * @describe:
 * @author: chenlong
 * @date: 2024/12/26 15:38
 */
@Data
public class ProductSetCodeDTO {
    /**
     * 选品集编码
     */
    private String productSetCode;
    /**
     * 选品集名称
     */
    private String productSetName;
    /**
     * 选品集下面商品的金额
     */
    private Long amount;
    /**
     * 商品信息
     */
    private List<ProductDTO> productList;
}
