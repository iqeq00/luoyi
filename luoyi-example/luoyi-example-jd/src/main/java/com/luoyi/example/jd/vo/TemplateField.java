package com.luoyi.example.jd.vo;

import lombok.Data;

/**
 * JSON 模板中的字段信息
 */
@Data
public class TemplateField {
    private String fieldName;
    private String headerName;
    private int sort;
}
