package com.luoyi.example.jd.cpt.hxt;

import com.alibaba.fastjson2.JSON;
import com.luoyi.example.jd.cpt.hlga.HLGAJLHttpClient;
import com.luoyi.example.jd.util.jd.JdSignUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 物流轨迹
 */
public class HXTJLHttpClient {
    private static final Logger log = LoggerFactory.getLogger(HLGAJLHttpClient.class);

    public static String post(TreeMap<String, Object> map) {
        // 获取配置
//        log.info("配置:{}", JSON.toJSONString(config));
        map.put("accessKey","v7OY5oqWcwIfz1unIJfRQJ4TNQJlelf4mXKPzWv0f3Q=");
        map.put("timestamp", System.currentTimeMillis() / 1000 + "");
        // 签名
        JdSignUtil.addSign(map,"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJB7LroBRqthF0L8CgzmGcTQZKJgXIbJTuAb096sA4Z+ESsq6kj1OMxFJYJFDaUQZ7vUOBWp5LHBg0Yxr+0GTlnd+ZeQwjPuzvtCA8cju2XzF3DncHMUWUZMwfeuNYr4g/IWakKh4KfRI30Bh+8vnzY0LedH9GHiAXq8kuFQ9GJPAgMBAAECgYBvYSbiaX1rPIvMLbQpwXD4EpahvptVpPbTOWCZdg25rpHZr1mxKKnCf0OtK0KbjwwI9k7OFIyio8VpZYBzvrrC0ACe8W1vvH9gcI+2urpjvfKMau38Vy/lB7raJfI5Op2gy9NQhsHIRyqIhfhkkY5+pfHbrbg9DR71IYonq84FUQJBAMYASJydvo1Chva0PYPsSWaVL6Obikzav+2vxjF1gyfFu8qmLNbLPlHD0RZ13TYcBM5LDGN/fTeTwt12+4ugDyMCQQC6zYtNdpxOjTB3X5gAoCCCdt9UqZwMHJjWeQkoxrko3Zxe0VQoprDTzsjUHKr4jfG+LDfqlpXlVrErmGuiN0jlAkEAlftjBVesHr61Ey1Gp6aVlyIMuwa4BS1yUu5h19cm6Tqr3H5x2qaL503IFRZk4Xbp4QYSNVA+HppL8owNUR+GNwJAaK6C3/gWA9cPT/YcAk25qiLfaFxb/FWSOK4/OcCTRplZ5bC8zsMK2MdKt8MQ9JTm4cF53FZizdBVHK8fshn1GQJABR2CdJV3Z9CPNixkR3hJ9LLU4/I6QhvClYe1yCQ+n/oI8ctVJuwPTze9ND3R/sgfMaTmlt3uNufrU1gUkrJSFQ==");
        return doPost("https://cvop.jd.com/component/http/JSON/youli/v1/order/orderTrack",map);
    }


    public static String doPost(String url, Map<String, Object> map) {
        log.info("请求参数:{}",map);
        CloseableHttpClient httpClient;
        HttpPost httpPost;
        String result = null;
        try {
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            //设置参数
            List<BasicNameValuePair> list = new ArrayList<>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
            }
        } catch (Exception e) {
            log.error("调用京东api异常,url:{},参数:{},异常:{}", url, JSON.toJSONString(map),e);
            throw new RuntimeException("调用异常,请稍后刷新重试");
        }
        return result;
    }

    public static void main(String[] args) {
        TreeMap<String, Object> signParamMap = new TreeMap<>();
        signParamMap.put("jdOrderId", "306482115618");
        String res = post(signParamMap);
        System.out.println(res);
    }

//    private ThirdSupplierConfigBO fetchData(String supplyCode) {
//        log.info("code:{}",supplyCode);
//        // key 为supplier_code,比如：JD_001
//        ThirdSupplierConfigReq req = new ThirdSupplierConfigReq();
//        req.setThirdSupplierType(supplyCode.split("_")[0]);
//        req.setBelongsSupplierCode(supplyCode);
//        return FeignUtil.rpc(supplierClient.getClientConfig(req));
//    }
//
//    /**
//     * 获取供应商配置
//     * @param supplyCode
//     * @return
//     */
//    private JLConfig getConfig(String supplyCode){
//        String s = redisTemplate.opsForValue().get(buildMtKey(supplyCode));
//        if (!StringUtils.hasLength(s)){
//            ThirdSupplierConfigBO bo = fetchData(supplyCode);
//            JLConfig config = JSONObject.parseObject(bo.getClientCallParam(), JLConfig.class);
//            putConfig(buildMtKey(supplyCode),config);
//            return config;
//        }
//        return JSONObject.parseObject(s, JLConfig.class);
//    }
//    private void putConfig(String key,JLConfig mtApiConfig){
//        redisTemplate.opsForValue().set(key, JSON.toJSONString(mtApiConfig));
//    }
//    private String buildMtKey(String code){
//        return CacheConstant.JL_CONFIG+code;
//    }
}
