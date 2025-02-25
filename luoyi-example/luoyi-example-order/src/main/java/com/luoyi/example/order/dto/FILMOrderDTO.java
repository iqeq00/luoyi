package com.luoyi.example.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FILMOrderDTO extends BaseOrderDTO {

    /**
     * 场次id
     */
    @NotBlank(message = "场次id不能为空")
    private String showId;
    /**
     * 总售价
     */
    @NotNull(message = "电影票金额不能为空")
    private Long saleAmount;
    /**
     * 电影票数量
     */
    @NotNull(message = "电影票数量不能为空")
    private Integer num;
    /**
     * 选品集code
     */
    @NotBlank(message = "选品集编码不能为空")
    private String selectionCode;
    /**
     * 选品集名称
     */
    private String selectionName;
    /**
     * 影院唯一ID，唯一标识一家影院
     */
    @NotBlank(message = "影院唯一ID不能为空")
    private String cinemaCode;
}
