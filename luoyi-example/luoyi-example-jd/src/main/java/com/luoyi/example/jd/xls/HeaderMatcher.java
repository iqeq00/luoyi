package com.luoyi.example.jd.xls;

import com.luoyi.example.jd.vo.TemplateField;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 匹配 JSON 模板中的表头和 Excel 文件中的表头
 */
public class HeaderMatcher {
    public static boolean matchHeaders(List<TemplateField> templateFields, List<String> excelHeaders) {
        List<String> templateHeaderNames = templateFields.stream()
                .map(TemplateField::getHeaderName)
                .collect(Collectors.toList());

        return templateHeaderNames.equals(excelHeaders);
    }
}

