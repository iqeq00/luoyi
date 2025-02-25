package com.luoyi.example.order.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EntityOrderDTO extends BaseOrderDTO {

    /**
     * 购物车凭证
     */
    @NotBlank(message = "购物车凭证不能为空")
    private String receipt;
    /**
     * 地址code
     */
    @NotBlank(message = "地址编码不能为空")
    private String addressCode;
}
