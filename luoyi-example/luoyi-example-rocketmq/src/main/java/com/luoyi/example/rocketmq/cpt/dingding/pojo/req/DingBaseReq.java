package com.luoyi.example.rocketmq.cpt.dingding.pojo.req;

import lombok.Data;

import java.io.Serializable;

/**
 * 钉钉机器人 - 系统请求参数
 *
 * @author yaojinchi
 * @date 2024-11-13
 */
@Data
public class DingBaseReq implements Serializable {

    /**
     * 类型
     */
    public String type;

    /**
     * json 字符串
     */
    public String json;

}