package com.luoyi.example.order.bean;

import lombok.Data;

@Data
public class SearchProductDTO extends BasePageQuery {

    private String keywords;
    private String desc;
}
