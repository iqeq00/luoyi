package com.luoyi.example.rocketmq.cpt.dingding.pojo.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * 青岛医护 - 返回data参数
 *
 * @author yaojinchi
 * @date 2024-11-06
 */
@Data
public class YihuBaseDataResp implements Serializable {

    /**
     * 请求唯一流水号
     */
    public String serialNo;

    /**
     * 错误信息
     */
    public String errorMsg;

    /**
     * 0为业务处理正常，其它为异常
     */
    public Integer code;

    /**
     * 加密数据(json字符串)
     */
    public String bizContent;

}