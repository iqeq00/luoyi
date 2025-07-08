package com.luoyi.example.jd.xls;

import com.luoyi.example.jd.vo.Card;
import com.luoyi.example.jd.vo.TemplateField;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DataTransformer {
    public static List<Card> transformData(List<TemplateField> templateFields, List<List<String>> excelData) {
        List<Card> result = new ArrayList<>();

        // 跳过表头，从第二行开始处理数据
        excelData.stream()
                .skip(1)
                .forEach(rowData -> {
                    Card card = new Card();
                    for (int i = 0; i < templateFields.size(); i++) {
                        TemplateField field = templateFields.get(i);
                        String fieldName = field.getFieldName();
                        String value = rowData.get(i);

                        try {
                            Field cardField = Card.class.getDeclaredField(fieldName);
                            cardField.setAccessible(true);
                            cardField.set(card, value);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            System.err.println("Field not found or access error: " + fieldName);
                        }
                    }
                    result.add(card);
                });
        return result;
    }
}
