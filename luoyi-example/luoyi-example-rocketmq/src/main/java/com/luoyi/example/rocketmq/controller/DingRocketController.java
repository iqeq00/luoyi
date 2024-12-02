package com.luoyi.example.rocketmq.controller;

import com.luoyi.example.rocketmq.producer.DingRocketMQProducer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DingRocketController {
    @Autowired
    private DingRocketMQProducer rocketMQProducer;

    @PostMapping("/mq/sendSync")
    public String sendSync(@RequestBody String message) {
        rocketMQProducer.sendSyncMessage(message);
        return "同步消息发送成功";
    }

    @GetMapping("/mq/sendAsync")
    public String sendAsync(@RequestParam String message) {
        rocketMQProducer.sendAsyncMessage(message);
        return "异步消息发送中";
    }

    @GetMapping("/mq/sendOneWay")
    public String sendOneWay(@RequestParam String message) {
        rocketMQProducer.sendOneWayMessage(message);
        return "单向消息发送成功";
    }
}