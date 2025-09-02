package com.luoyi.example.card.controller;

import com.luoyi.example.card.service.XlsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/xls")
public class XlsController {

    private final XlsService xlsService;

    public XlsController(XlsService xlsService) {
        this.xlsService = xlsService;
    }

    @PostMapping("/process-and-export")
    public void processAndExport(@RequestParam("file") MultipartFile file,
                                 @RequestParam("keyParam") String keyParam, // 新增参数
                                 HttpServletResponse response) throws IOException {
        xlsService.processAndExport(keyParam, file, response);
    }
}
