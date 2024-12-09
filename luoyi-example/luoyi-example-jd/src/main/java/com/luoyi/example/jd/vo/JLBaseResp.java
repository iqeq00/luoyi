package com.luoyi.example.jd.vo;

import lombok.Data;

/**
 * 京东锦礼返回基类
 */
@Data
public class JLBaseResp {
    private Boolean success;
    /**
     * 错误描述
     */
    private String resultMessage;
    /**
     * 错误码
     */
    private String resultCode;
    /**
     * 返回对象
     */
    private String result;
}
