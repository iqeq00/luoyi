package com.luoyi.example.rocketmq.cpt.dingding.service;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.luoyi.example.rocketmq.cpt.dingding.enums.DingTalkMsgTypeEnum;
import com.luoyi.example.rocketmq.cpt.dingding.pojo.dto.DingTalkRobotLinkDTO;
import com.luoyi.example.rocketmq.cpt.dingding.pojo.dto.DingTalkRobotMarkDownDTO;
import com.luoyi.example.rocketmq.cpt.dingding.pojo.dto.DingTalkRobotTextDTO;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


/**
 * https://open.dingtalk.com/document/robots/custom-robot-access
 * https://open.dingtalk.com/document/orgapp/custom-bot-to-send-group-chat-messages?spm=ding_open_doc.document.0.0.1a115e59sDs9Sz
 *
 * https://blog.csdn.net/Blueeyedboy521/article/details/129548220?ops_request_misc=&request_id=&biz_id=102&utm_term=springboot%E5%8F%91%E9%80%81%E7%BB%99%E9%92%89%E9%92%89%E9%80%9A%E7%9F%A5&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-3-129548220.142%5Ev99%5Epc_search_result_base6&spm=1018.2226.3001.4187
 */
@Slf4j
@Service
public class DingTalkRobotNewService {

    @Value("${dingers.ding-talk.open-api}")
    private String openApi;

    @Value("${dingers.ding-talk.access-token}")
    private String accessToken;

    @Value("${dingers.ding-talk.prefix}")
    private String prefix;

    @Value("${dingers.ding-talk.secret}")
    private String secret;

    private String algorithm = "HmacSHA256";

    /**
     * 获取请求地址，签名
     */
    private String getRequestUrl() {
        try {
            Long timestamp = System.currentTimeMillis();
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), algorithm));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)),StandardCharsets.UTF_8);
            return openApi + "?access_token=" + accessToken + "&timestamp=" + timestamp + "&sign=" + sign;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取客户端
     */
    public DingTalkClient getClient() {

        return new DefaultDingTalkClient(getRequestUrl());
    }

    /**
     * 发送文本信息
     */
    public void sendText(DingTalkRobotTextDTO dingTalkRobotTextDTO) {

        DingTalkClient client =  getClient();
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(DingTalkMsgTypeEnum.TEXT.getType());
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent("[" + prefix + "]" + dingTalkRobotTextDTO.getContent());
        request.setText(text);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setIsAtAll(false);
//        at.setAtMobiles(Arrays.asList("1392xxxxx","155xxxx"));
        request.setAt(at);
        try {
            OapiRobotSendResponse response = client.execute(request);
            log.info("success:{}, code:{}, errorCode:{}, errorMsg:{}",response.isSuccess(),response.getCode(),response.getErrcode(),response.getErrmsg());
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 发送链接信息
     */
    public void sendLink(DingTalkRobotLinkDTO dingTalkRobotLinkDTO) {

        DingTalkClient client =  getClient();
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(DingTalkMsgTypeEnum.LINK.getType());
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        link.setMessageUrl(dingTalkRobotLinkDTO.getMessageUrl());
        link.setPicUrl(dingTalkRobotLinkDTO.getPicUrl());
        link.setTitle(dingTalkRobotLinkDTO.getTitle());
        link.setText(dingTalkRobotLinkDTO.getText());
        request.setLink(link);
        try {
            OapiRobotSendResponse response = client.execute(request);
            log.info("success:{}, code:{}, errorCode:{}, errorMsg:{}",response.isSuccess(),response.getCode(),response.getErrcode(),response.getErrmsg());
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendMarkDown(DingTalkRobotMarkDownDTO dingTalkRobotMarkDownDTO) {

        DingTalkClient client = getClient();
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        request.setMsgtype(DingTalkMsgTypeEnum.MARKDOWN.getType());
//        markdown.setText(dingTalkRobotMarkDownDTO.getText());
//        markdown.setTitle(dingTalkRobotMarkDownDTO.getTitle());
        markdown.setTitle("杭州天气");
        markdown.setText("#### 杭州天气 \n" +
                "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
                "> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n"  +
                "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n");
        request.setMarkdown(markdown);

        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
//        at.setAtMobiles(Arrays.asList("1392xxxxx","155xxxx"));
        request.setAt(at);
        try {
            OapiRobotSendResponse response = client.execute(request);
            System.out.printf("success:%s, code:%s, errorCode:%s, errorMsg:%s%n",response.isSuccess(),response.getCode(),response.getErrcode(),response.getErrmsg());
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

    }


    public void sendActionCard(){
        DingTalkClient client = getClient();
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        OapiRobotSendRequest.Actioncard card = new OapiRobotSendRequest.Actioncard();
        request.setMsgtype(DingTalkMsgTypeEnum.ACTIONCARD.getType());
        card.setTitle("乔布斯 20 年前想打造一间苹果咖啡厅，而它正是 Apple Store 的前身");
        card.setText("![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png) \n" +
                " ### 乔布斯 20 年前想打造的苹果咖啡厅 \n" +
                " Apple Store 的设计正从原来满满的科技感走向生活化，而其生活化的走向其实可以追溯到 20 年前苹果一个建立咖啡馆的计");
        // 0：按钮竖直排列, 1：按钮横向排列
        card.setBtnOrientation("0");
        card.setSingleTitle("阅读全文");
        card.setSingleURL("https://www.dingtalk.com/");
        request.setActionCard(card);

        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
//        at.setAtMobiles(Arrays.asList("1392xxxxx","155xxxx"));
        request.setAt(at);
        try {
            OapiRobotSendResponse response = client.execute(request);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendFreeCard(){
        DingTalkClient client =  getClient();
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        OapiRobotSendRequest.Feedcard card = new OapiRobotSendRequest.Feedcard();
        request.setMsgtype(DingTalkMsgTypeEnum.FEEDCARD.getType());
        List<OapiRobotSendRequest.Links> data = new ArrayList<>();
        OapiRobotSendRequest.Links links1 = new OapiRobotSendRequest.Links();
        OapiRobotSendRequest.Links links2 = new OapiRobotSendRequest.Links();
        OapiRobotSendRequest.Link link1 = new OapiRobotSendRequest.Link();
        link1.setText("标题1");
        link1.setTitle("时代的火车向前开1");
        link1.setPicUrl("https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png");
        link1.setMessageUrl("https://www.dingtalk.com/");
        links1.setTitle("时代的火车向前开1");
        links1.setPicURL("https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png");
        links1.setMessageURL("https://www.dingtalk.com/");
        data.add(links1);
        links2.setTitle("时代的火车向前开2");
        links2.setPicURL("https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png");
        links2.setMessageURL("https://www.dingtalk.com/");
        data.add(links2);
        card.setLinks(data);
        request.setFeedCard(card);

        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
//        at.setAtMobiles(Arrays.asList("1392xxxxx","155xxxx"));
        request.setAt(at);
        try {
            OapiRobotSendResponse response = client.execute(request);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

}
