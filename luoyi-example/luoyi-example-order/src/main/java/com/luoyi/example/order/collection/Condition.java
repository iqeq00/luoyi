package com.luoyi.example.order.collection;

import lombok.Data;

import java.util.List;

/**
 * 条件实体类，与JSON结构对应
 *
 * @author yaojinchi
 */
@Data
public class Condition {

    // 字段名称
    private String field;
    // 条件类型
    private String type;
    // 条件值列表
    private List<String> values;

}