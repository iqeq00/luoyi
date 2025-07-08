package com.luoyi.example.jd.xls;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 读取 Excel 文件
 */
public class ExcelReader {
    public static List<List<String>> readExcel(InputStream inputStream) {
        List<List<String>> data = new ArrayList<>();

        EasyExcel.read(inputStream, new ReadListener<Map<Integer, String>>() {
            private List<String> headers = null;
            private boolean isHeader = true;

            @Override
            public void invoke(Map<Integer, String> object, AnalysisContext context) {
                if (isHeader) {
                    // 第一行是表头，按索引顺序创建表头列表
                    headers = new ArrayList<>(Collections.nCopies(object.size(), null));
                    object.forEach((index, value) -> headers.set(index, value));
                    data.add(headers);
                    isHeader = false;
                } else {
                    // 其他行是数据，按索引顺序创建数据行
                    List<String> row = new ArrayList<>(Collections.nCopies(headers.size(), null));
                    object.forEach((index, value) -> {
                        if (index < row.size()) {
                            row.set(index, value);
                        }
                    });
                    data.add(row);
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                // 确保至少有表头
                if (headers != null && data.isEmpty()) {
                    data.add(headers);
                }
            }
        }).headRowNumber(0).sheet().doRead();

        return data;
    }
}