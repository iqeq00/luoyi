package com.luoyi.example.jd.vo;

import lombok.Data;

/**
 * 用于存储 Excel 数据
 */
@Data
public class Card {
    private String cardNo;
    private String cardPassword;
    private String legalStartTime;
    private String legalEndTime;
    private String cardValue;
    // 可以根据需要添加更多字段
}
