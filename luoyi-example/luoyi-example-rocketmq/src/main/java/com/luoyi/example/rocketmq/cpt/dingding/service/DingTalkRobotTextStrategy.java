package com.luoyi.example.rocketmq.cpt.dingding.service;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.luoyi.example.rocketmq.cpt.dingding.config.DingTalkConfig;
import com.luoyi.example.rocketmq.cpt.dingding.enums.DingTalkMsgTypeEnum;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DingTalkRobotTextStrategy implements DingTalkRobotStrategy {

    @Autowired
    private DingTalkConfig dingTalkConfig;

    @Override
    public void sendMessage(String url, String jsonStr) {

//        DingTextReq req = JSON.parseObject(jsonStr, DingTextReq.class);
//        if (ObjectUtil.isNotNull(req)) {
//            DingTalkClient client =  new DefaultDingTalkClient(url);
//            OapiRobotSendRequest request = new OapiRobotSendRequest();
//            request.setMsgtype(DingTalkMsgTypeEnum.TEXT.getType());
//            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
//            text.setContent("["+dingTalkConfig.getPrefix()+"]" + req.getContent());
//            request.setText(text);
//            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
//            at.setIsAtAll(false);
////        at.setAtMobiles(Arrays.asList("1392xxxxx","155xxxx"));
//            request.setAt(at);
//            try {
//                OapiRobotSendResponse response = client.execute(request);
//                log.info("success:{}, code:{}, errorCode:{}, errorMsg:{}",response.isSuccess(),response.getCode(),response.getErrcode(),response.getErrmsg());
//            } catch (ApiException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    @Override
    public DingTalkMsgTypeEnum getDingTalkMsgType() {
        return DingTalkMsgTypeEnum.TEXT;
    }
}
