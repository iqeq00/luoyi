package com.luoyi.example.rocketmq.consumer;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.luoyi.example.rocketmq.cpt.dingding.pojo.req.DingBaseReq;
import com.luoyi.example.rocketmq.cpt.dingding.service.DingTalkRobotContext;
import com.luoyi.example.rocketmq.cpt.dingding.service.DingTalkRobotNewService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "dingding-topic", consumerGroup = "consumer-group-dingding")
public class DingRocketMQConsumer implements RocketMQListener<String> {

//    @Autowired
//    private DingtalkRobotService dingtalkRobotService;
//
    @Autowired
    private DingTalkRobotNewService dingtalkRobotNewService;

    @Autowired
    private DingTalkRobotContext dingTalkRobotContext;

    @Override
    public void onMessage(String jsonStr) {

        System.out.printf("收到消息: %s\n", jsonStr);
//        dingtalkRobotService.dingTalk(s);

        DingBaseReq dingBaseReq = JSON.parseObject(jsonStr, DingBaseReq.class);
        if (ObjectUtil.isNotNull(dingBaseReq)) {
            dingTalkRobotContext.sendRobotMessage(dingBaseReq);
        }
    }
}
