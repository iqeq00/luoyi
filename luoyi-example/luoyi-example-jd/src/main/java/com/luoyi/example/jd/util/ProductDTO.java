package com.luoyi.example.jd.util;

import lombok.Data;

/**
 * @describe:
 * @author: chenlong
 * @date: 2024/12/26 15:39
 */
@Data
public class ProductDTO {
    /**
     * 商品sku
     */
    private String sku;
    /**
     * 商品图片
     */
    private String img;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品数量
     */
    private Integer num;
}
