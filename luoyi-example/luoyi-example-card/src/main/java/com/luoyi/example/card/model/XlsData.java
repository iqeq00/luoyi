package com.luoyi.example.card.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class XlsData implements Serializable {

    @ExcelProperty(value = "卡号", index = 0)
    private String cardNo;

    @ExcelProperty(value = "加密卡密", index = 1)
    private String cardPassword;

    @ExcelProperty(value = "盐值", index = 2)
    private String saltValue;

    @ExcelProperty(value = "原始卡密", index = 3)
    private String password;
}
