package com.luoyi.example.rocketmq.cpt.dingding.pojo.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * 青岛医护 - 返回参数
 *
 * @author yaojinchi
 * @date 2024-11-06
 */
@Data
public class YihuBaseResp implements Serializable {

    /**
     * 0为请求正常，其它为异常
     */
    public Integer code;

    /**
     * 返回文字描述
     */
    public String msg;

    /**
     * 返回数据
     */
    public YihuBaseDataResp data;

}