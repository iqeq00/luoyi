package com.luoyi.example.jd.controller;

//import com.luoyi.example.jd.cpt.vop.JDServiceApi;

import com.alibaba.excel.EasyExcel;
import com.luoyi.example.jd.vo.Card;
import com.luoyi.example.jd.vo.TemplateField;
import com.luoyi.example.jd.xls.DataTransformer;
import com.luoyi.example.jd.xls.ExcelReader;
import com.luoyi.example.jd.xls.HeaderMatcher;
import com.luoyi.example.jd.xls.TemplateParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class XLSController {

//    @Autowired
//    private JDServiceApi jdServiceApi;


    @PostMapping("/api/upload/xls")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file,
                                                  @RequestParam("template") String jsonTemplate) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("文件为空!");
        }

        try {
            // 解析 JSON 模板
            List<TemplateField> templateFields = TemplateParser.parseTemplate(jsonTemplate);

            // 从 MultipartFile 中读取 Excel 文件内容
            List<List<String>> excelData = ExcelReader.readExcel(file.getInputStream());

            // 提取表头
            List<String> excelHeaders = excelData.get(0);

            // 这里 需要指定读用哪个class去读，然后读取第一个sheet
//            EasyExcel.read(file.getInputStream(), DemoData.class, new DemoHeadDataListener()).sheet().doRead();

            // 匹配表头
            if (!HeaderMatcher.matchHeaders(templateFields, excelHeaders)) {
                return ResponseEntity.badRequest().body("Header mismatch!");
            }

            // 转换数据
            List<Card> transformedData = DataTransformer.transformData(templateFields, excelData);

            // 返回处理结果
            String result = transformedData.stream()
                    .map(Card::toString)
                    .collect(Collectors.joining("\n"));
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file: " + e.getMessage());
        }
    }
}