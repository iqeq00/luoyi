package com.luoyi.example.rocketmq.cpt.dingding.service;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import lombok.Data;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Deprecated
@Service
public class DingtalkRobotService {

    // 普通群：a18fe48cdbbd87a4266dadb9d117b8cf42148c26a3f9f75e7d35fd3e8efe1d18
    public static final String CUSTOM_ROBOT_TOKEN = "a18fe48cdbbd87a4266dadb9d117b8cf42148c26a3f9f75e7d35fd3e8efe1d18";
    // 内部群：3c9e01a35fff292d1442adda7739ebc9e0560c6933c79cd61e60c55e28aff92d
//    public static final String CUSTOM_ROBOT_TOKEN = "3c9e01a35fff292d1442adda7739ebc9e0560c6933c79cd61e60c55e28aff92d";

//    public static final String USER_ID= "<you need @ group user's userId>";

    // 普通群
    public static final String SECRET = "SEC121035cd8e5970da0ec79ef4e66e44ab17bada751780025c5991ae977e711a39";
    // 内部群
//    public static final String SECRET = "SEC8e06b5852174860036623c220bcd522d766a4b91b038dc8549a4b465211f8b89";

    public void dingTalk(String content) {
        try {
            Long timestamp = System.currentTimeMillis();
            System.out.println(timestamp);
            String secret = SECRET;
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
            System.out.println(sign);

            //sign字段和timestamp字段必须拼接到请求URL上，否则会出现 310000 的错误信息
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?sign="+sign+"&timestamp="+timestamp);
            OapiRobotSendRequest req = new OapiRobotSendRequest();
            /**
             * 发送文本消息
             */
            //定义文本内容
            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
            text.setContent(content);
            //定义 @ 对象
            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
//            at.setAtUserIds(Arrays.asList(USER_ID));
            //明确为 true。通知时@所有人
            at.setIsAtAll(true);

            //设置消息类型
            req.setMsgtype("text");
            req.setText(text);
            req.setAt(at);
            OapiRobotSendResponse rsp = client.execute(req, CUSTOM_ROBOT_TOKEN);
            System.out.println(rsp.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }


}
