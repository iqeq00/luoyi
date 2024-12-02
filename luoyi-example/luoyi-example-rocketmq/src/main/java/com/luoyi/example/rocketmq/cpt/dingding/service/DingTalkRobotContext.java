package com.luoyi.example.rocketmq.cpt.dingding.service;

import cn.hutool.core.util.ObjectUtil;
import com.luoyi.example.rocketmq.cpt.dingding.config.DingTalkConfig;
import com.luoyi.example.rocketmq.cpt.dingding.enums.DingTalkMsgTypeEnum;
import com.luoyi.example.rocketmq.cpt.dingding.pojo.req.DingBaseReq;
import jakarta.annotation.PostConstruct;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 钉钉自定义机器人接入 策略上下文
 *
 * @author lichee
 * @date 2024-11-13
 */
@Service
public class DingTalkRobotContext {

    @Autowired
    private DingTalkConfig dingTalkConfig;

    @Autowired
    private List<DingTalkRobotStrategy> dingTalkStrategyList;

    private static Map<DingTalkMsgTypeEnum, DingTalkRobotStrategy> dingTalkStrategyMap;

    @PostConstruct
    public void init() {
        dingTalkStrategyMap = dingTalkStrategyList.stream().collect(Collectors.toMap(DingTalkRobotStrategy::getDingTalkMsgType, a -> a));
    }


    public void sendRobotMessage(DingBaseReq dingBaseReq) {
        DingTalkMsgTypeEnum dingTalkMsgType = DingTalkMsgTypeEnum.getEnum(dingBaseReq.getType());
        if (ObjectUtil.isNotNull(dingTalkMsgType)) {
            dingTalkStrategyMap.get(dingTalkMsgType).sendMessage(getRequestUrl(), dingBaseReq.getJson());
        }
    }

    /**
     * 获取请求地址，签名
     */
    private String getRequestUrl() {
        try {
            Long timestamp = System.currentTimeMillis();
            String stringToSign = timestamp + "\n" + dingTalkConfig.getSecret();
            Mac mac = Mac.getInstance(dingTalkConfig.getAlgorithm());
            mac.init(new SecretKeySpec(dingTalkConfig.getSecret().getBytes(StandardCharsets.UTF_8), dingTalkConfig.getAlgorithm()));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), StandardCharsets.UTF_8);
            return dingTalkConfig.getOpenApi() + "?access_token=" + dingTalkConfig.getAccessToken() + "&timestamp=" + timestamp + "&sign=" + sign;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

}
