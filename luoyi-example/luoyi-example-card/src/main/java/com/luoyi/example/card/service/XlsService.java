package com.luoyi.example.card.service;

import com.alibaba.excel.EasyExcel;
import com.luoyi.example.card.listener.XlsDataListener;
import com.luoyi.example.card.model.XlsData;
import com.luoyi.example.card.util.EncryptUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class XlsService {

    public void processAndExport(String key, MultipartFile file, HttpServletResponse response) throws IOException {
        // 1. 读取Excel数据
        List<XlsData> dataList = EasyExcel.read(file.getInputStream(), XlsData.class, new XlsDataListener())
                .sheet().doReadSync();
        log.info("dataList 原始长度:{}", dataList.size());

        // 2. 业务处理
        List<XlsData> processedData = processBusinessData(key, dataList);
        log.info("dataList 业务处理后长度:{}", processedData.size());

        // 3. 导出处理后的数据
        exportToResponse(processedData, response);
    }

    private List<XlsData> processBusinessData(String key, List<XlsData> dataList) {

        dataList.forEach(data -> {
            data.setPassword(EncryptUtil.getDecryptStr(key, data.getCardPassword(), data.getSaltValue()));
        });
//        dataList.stream().filter(Objects::nonNull).map(v -> v.setPassword(EncryptUtil.getDecryptStr(key, v.getCardPassword(), v.getSaltValue()))).collect(Collectors.toList());
        return dataList;
    }

    private void exportToResponse(List<XlsData> dataList, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("处理后的业务数据", StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), XlsData.class)
                .sheet("业务数据")
                .doWrite(dataList);
    }
    
}