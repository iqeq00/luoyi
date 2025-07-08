package com.luoyi.example.jd.validation;

import cn.hutool.core.collection.CollUtil;
import com.luoyi.example.jd.vo.TemplateField;
import com.luoyi.example.jd.xls.TemplateParser;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainTest {

    public static void main(String[] args) {

//        String ms = "TM25YI813YOK6M";
//        System.out.println(ms.hashCode());
//        System.out.println(ms.substring(0, ms.length() - 6) );
//
//        String jsonTemplate = "[{'fieldName':'cardNo','headerName':'卡号','sort':0},{'fieldName':'cardPassword','headerName':'卡密','sort':1},{'fieldName':'cardValue','headerName':'面值','sort':3}]";
//        // 解析 JSON 模板
//        List<TemplateField> templateFields = TemplateParser.parseTemplate(jsonTemplate);
//
//        SupplierCardImportDTO importDTO = new SupplierCardImportDTO();
//        importDTO.setCardValue("11.00");
//
//        // 静态调用 ValidationUtils
//        ValidationResult result = ValidationUtils.validateFields(importDTO, templateFields.stream().map(x -> x.getFieldName()).collect(Collectors.toList()));
//
//        System.out.println(result.isValid());
//        if (!result.isValid()) {
//            System.out.println(result.toErrorMessage());
//        }

        List<String> rpItemIds = Arrays.asList("136");
        if (CollUtil.isNotEmpty(rpItemIds) && !rpItemIds.contains("44")) {
            System.out.println("111");
        }
    }
}
