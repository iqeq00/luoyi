package com.luoyi.example.jd.xls;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.luoyi.example.jd.vo.TemplateField;

import java.util.List;

/**
 * 解析 JSON 模板
 */
public class TemplateParser {
    public static List<TemplateField> parseTemplate(String jsonTemplate) {
        return JSON.parseObject(jsonTemplate, new TypeReference<List<TemplateField>>() {});
    }
}
