package com.luoyi.example.rocketmq.cpt.dingding.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 钉钉机器人 - 链接请求参数
 *
 * @author yaojinchi
 * @date 2024-11-13
 */
@Data
public class DingTalkRobotLinkDTO implements Serializable {

    /**
     * 标题
     */
    private String title;

    /**
     * 文本
     */
    private String text;

    /**
     * 图片地址
     */
    private String picUrl;

    /**
     * 链接地址
     */
    private String messageUrl;

}