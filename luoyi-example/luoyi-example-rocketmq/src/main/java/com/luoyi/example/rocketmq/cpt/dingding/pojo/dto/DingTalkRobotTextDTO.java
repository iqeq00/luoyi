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
public class DingTalkRobotTextDTO implements Serializable {

    /**
     * 内容
     */
    public String content;

}