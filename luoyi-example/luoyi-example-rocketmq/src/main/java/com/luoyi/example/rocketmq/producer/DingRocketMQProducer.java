package com.luoyi.example.rocketmq.producer;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DingRocketMQProducer {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    private final String topic = "dingding-topic";

    // 1.同步发送消息
    // 同步发送是指发送方发送一条消息后，会等待服务器返回确认信息后再进行后续操作。这种方式适用于需要可靠性保证的场景。
    public void sendSyncMessage(String message){
        rocketMQTemplate.syncSend(topic, message);
        System.out.printf("同步发送结果: %s\n", message);
    }

    // 2.异步发送消息
    // 异步发送是指发送方发送消息后，不等待服务器返回确认信息，而是通过回调接口处理返回结果。这种方式适用于对响应时间要求较高的场景。
    public void sendAsyncMessage(String message){
        rocketMQTemplate.asyncSend(topic, message, new SendCallback() {

            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.printf("异步发送成功: %s\n", sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.printf("异步发送失败: %s\n", throwable.getMessage());
            }
        });
    }

    // 3.单向发送消息
    // 单向发送是指发送方只负责发送消息，不关心服务器的响应。该方式适用于对可靠性要求不高的场景，如日志收集。
    public void sendOneWayMessage(String message){
        rocketMQTemplate.sendOneWay(topic, message);
        System.out.println("单向消息发送成功");
    }
}
