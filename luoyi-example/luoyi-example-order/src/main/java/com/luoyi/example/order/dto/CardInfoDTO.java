package com.luoyi.example.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @describe: 卡券信息
 * @author: chenlong
 * @date: 2023/12/5 11:19
 */
@Data
public class CardInfoDTO {

    /**
     * 卡券数量
     */
    @NotNull(message = "卡券数量不能为空")
    private Integer num;
    /**
     * 选品集code
     */
    @NotBlank(message = "选品集编码不能为空")
    private String selectionCode;
    /**
     * 销售单价
     */
    @NotNull(message = "销售单价不能为空")
    private Long salePrice;
    /**
     * 充值账号
     */
    private String rechargeAccount;
    /**
     * 账号类型:QQ,TEL,WX,MAIL,OTHER
     */
    private String accountType;
    /**
     * 卡券code
     */
    @NotBlank(message = "卡券编码不能为空")
    private String cardCode;
    /**
     * 商品code
     */
    @NotBlank(message = "商品编码不能为空")
    private String productCode;
    /**
     * 供应商code
     */
    @NotBlank(message = "供应商编码不能为空")
    private String supplierCode;
    /**
     * 卡券规格
     */
    private String cardSpec;
    /**
     * 规格id
     */
    private Integer specValueId;
    /**
     * 品牌id
     */
    private Long brandId;
    /**
     * 品牌名字
     */
    private String brandName;
    /**
     * 姓氏
     */
    private String lastName;
    /**
     * 名字
     */
    private String firstName;

}