//package com.luoyi.example.jd.cpt.vop;
//
//import cn.hutool.core.date.DatePattern;
//import cn.hutool.core.map.MapUtil;
//import com.alibaba.fastjson2.JSON;
//import com.luoyi.example.jd.util.HttpUtils;
//import com.luoyi.example.jd.util.jd.JdSignUtil;
//import com.luoyi.example.jd.vo.JLBaseResp;
//import com.luoyi.example.jd.vo.JLTokenInfo;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.time.DateFormatUtils;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//import java.util.concurrent.TimeUnit;
//
//@Slf4j
//@Service
//public class JDServiceApi {
//
//    String logisticsUrl = "https://cvop.jd.com/component/http/JSON/youli/v1/order/orderTrack";
//    String tokenUrl = "https://cvop.jd.com/oauth2/accessToken";
//
//
//    /**
//     * 通过订单号查询物流信息
//     */
//    public void getLogistics(String jdOrderId) {
//
//        HashMap<String, Object> imap = MapUtil.newHashMap(1 << 4);
//        imap.put("token", "H1MhfH6l4PjhafTKGX2t76Y1M");
//        imap.put("jdOrderId", jdOrderId);
//        imap.put("waybillCode", "1");
//
//        String res = doPost(logisticsUrl, imap);
//        log.info("获取配送信息返回结果={}", res);
//    }
////    JDJL("和乐关爱锦礼", "jd123456", "Jk59O7G1T9UiAs4G7aEc", "Kz3E4KsFLtAmLYYgHX4f","https://bizapi.jd.com"),
////    private String userName;
////    private String passWord;
////    private String clientId;
////    private String clientSecret;
////    private String apiHost;
//
//    private String getJlVopToken() {
//        Map<String, Object> params = new HashMap(1 << 4);
//        // 固定值 access_token
//        String grantType = "access_token";
//        params.put("grant_type", grantType);
//        // 用户名
//        String username = "和乐关爱锦礼";
//        params.put("username", username);
//        // 加密后的密码
//        String password = JdSignUtil.getMD5Str("jd123456");
//        params.put("password", password);
//        // 当前时间 格式yyyy-MM-dd HH:mm:ss
//        String timestamp = DateFormatUtils.format(System.currentTimeMillis(), DatePattern.NORM_DATETIME_PATTERN);
//        params.put("timestamp", timestamp);
//        // 对接账号
//        String clientId = "Jk59O7G1T9UiAs4G7aEc";
//        params.put("client_id", clientId);
//        // 对接账号密钥
//        String clientSecret = "Kz3E4KsFLtAmLYYgHX4f";
//        // 认证标识
//        String sign = clientSecret + timestamp + clientId + username + password + grantType + clientSecret;
//        log.info("加密前的sign={}", sign);
//        sign = JdSignUtil.getMD5Str(sign).toUpperCase();
//        params.put("sign", sign);
//        log.info("加密后的sign={}", sign);
//        // 执行请求
//        String rs = doPost(tokenUrl, params);
//        log.info("获取token返回结果={}", rs);
//        JLBaseResp res = JSON.parseObject(rs, JLBaseResp.class);
//        if (res.getSuccess()) {
//            JLTokenInfo vopToken = JSON.parseObject(res.getResult(), JLTokenInfo.class);
//            return vopToken.getAccess_token();
//        }
//        return null;
//    }
//
//
//    public String doPost(String url, Map<String, Object> map) {
//        log.info("请求参数:{}",map);
//        CloseableHttpClient httpClient;
//        HttpPost httpPost;
//        String result = null;
//        try {
//            httpClient = HttpClients.createDefault();
//            httpPost = new HttpPost(url);
//            //设置参数
//            List<BasicNameValuePair> list = new ArrayList<>();
//            Iterator iterator = map.entrySet().iterator();
//            while (iterator.hasNext()) {
//                Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
//                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
//            }
//            if (list.size() > 0) {
//                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
//                httpPost.setEntity(entity);
//            }
//            HttpResponse response = httpClient.execute(httpPost);
//            if (response != null) {
//                HttpEntity resEntity = response.getEntity();
//                if (resEntity != null) {
//                    result = EntityUtils.toString(resEntity, "UTF-8");
//                }
//            }
//        } catch (Exception e) {
//            log.error("调用京东api异常,url:{},参数:{},异常:{}", url, e);
//        }
//        return result;
//    }
//}
