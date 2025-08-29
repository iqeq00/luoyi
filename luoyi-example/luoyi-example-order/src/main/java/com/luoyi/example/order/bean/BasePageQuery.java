package com.luoyi.example.order.bean;

import lombok.Data;

@Data
public class BasePageQuery {

    /**
     * 页码
     */
    private int pageNum = 1;

    /**
     * 条数
     */
    private int pageSize = 10;
}
