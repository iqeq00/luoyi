package com.luoyi.example.rocketmq.cpt.dingding.service;

import com.luoyi.example.rocketmq.cpt.dingding.enums.DingTalkMsgTypeEnum;

/**
 * 钉钉自定义机器人接入策略
 *
 * @author lichee
 * @date 2024-11-13
 */
public interface DingTalkRobotStrategy {

    /**
     * 发送消息
     */
    void sendMessage(String url, String jsonStr);

    /**
     * 返回具体类型
     */
    DingTalkMsgTypeEnum getDingTalkMsgType();
}
