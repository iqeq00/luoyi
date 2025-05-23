package com.luoyi.example.order.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "sceneType", visible = true)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = CardOrderDTO.class, name = "CARD"),
        @JsonSubTypes.Type(value = EntityOrderDTO.class, name = "ENTITY"),
        @JsonSubTypes.Type(value = FilmOrderDTO.class, name = "FILM")
})
public class BaseOrderDTO {

    /**
     * 场景类型
     */
    @NotBlank(message = "场景类型不能为空")
    private String sceneType;
    /**
     * 订单备注
     */
    private String note;
    /**
     * 专项id
     */
    private Integer itemId;
    /**
     * 优惠券code
     */
    private String couponCode;
    /**
     * 红包
     */
    private List<Integer> rpItemIds;
    /**
     * 版本标识 true 新版本  false 旧版本
     */
    private Boolean versionFlag = false;
    /**
     * 是否是外部应用
     */
    private Boolean isOutApp = false;

}