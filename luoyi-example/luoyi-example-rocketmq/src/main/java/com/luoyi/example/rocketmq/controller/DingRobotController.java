package com.luoyi.example.rocketmq.controller;

import com.luoyi.example.rocketmq.cpt.dingding.pojo.dto.DingTalkRobotLinkDTO;
import com.luoyi.example.rocketmq.cpt.dingding.pojo.dto.DingTalkRobotMarkDownDTO;
import com.luoyi.example.rocketmq.cpt.dingding.pojo.dto.DingTalkRobotTextDTO;
import com.luoyi.example.rocketmq.cpt.dingding.service.DingTalkRobotNewService;
import com.luoyi.example.rocketmq.producer.DingRocketMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DingRobotController {
    @Autowired
    private DingTalkRobotNewService dingTalkRobotNewService;

    @PostMapping("/robot/text")
    public String text(@RequestBody DingTalkRobotTextDTO dingTalkRobotTextDTO) {

        dingTalkRobotNewService.sendText(dingTalkRobotTextDTO);
        return "ok";
    }

    @PostMapping("/robot/link")
    public String link(@RequestBody DingTalkRobotLinkDTO dingTalkRobotLinkDTO) {

        dingTalkRobotNewService.sendLink(dingTalkRobotLinkDTO);
        return "ok";
    }

    @PostMapping("/robot/markdown")
    public String markdown(@RequestBody DingTalkRobotMarkDownDTO dingTalkRobotMarkDownDTO) {

        dingTalkRobotNewService.sendMarkDown(dingTalkRobotMarkDownDTO);
        return "ok";
    }

    @PostMapping("/robot/actionCard")
    public String actionCard() {

        dingTalkRobotNewService.sendActionCard();
        return "ok";
    }

    @PostMapping("/robot/freeCard")
    public String freeCard() {

        dingTalkRobotNewService.sendFreeCard();
        return "ok";
    }

}