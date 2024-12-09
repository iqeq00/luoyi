package com.luoyi.example.jd.cpt.hlga;

import com.alibaba.fastjson2.JSON;
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

public class HLGAJLSubOrderHttpClient {
    private static final Logger log = LoggerFactory.getLogger(HLGAJLSubOrderHttpClient.class);

    public static String post(TreeMap<String, Object> map) {
        // 获取配置
//        log.info("配置:{}", JSON.toJSONString(config));
        map.put("accessKey","zbaUgj9N1CZU0Zsi_stBU4FJ6yq04WS1Z3qDT3cXC5s=");
        map.put("timestamp", System.currentTimeMillis() / 1000 + "");
        // 签名
        JdSignUtil.addSign(map,"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKJpdDRccPcgNpB4ehJnlXZIZWrg2sYRnKHQtpbxUFV63Jfz5PAXmcD+b3DS90F1HpM9ejGzFr2Tj46I0LfTunbbVZX2cRhY3ZNmauuG4Ap2TKUwy6QhNbeIBHoxQ1C1EqA+ALGkfjij9qIye2kfDIR2t4fJ6CH5kBO4CM9Mb+ZxAgMBAAECgYAMVNVt5FbvbJK4BW2/V/4RLQ4GP2xc/ZlW7/HtJAVleB6nqu0HDcOu06LCfkInxiy8IT7V5zMi0cFkoR631muh26sELNkBMxxx0TjX0v1DrbVNZa8stfLP2mpFDkYXOGg13bobT4F5NDDhyeDcA+Ne/m7tQTVtEoKKHDrRRp3gAQJBANInKKWdeLlb5r+syUGmt1/ZDcgwglTmfHI7siJE3fU4r5OFtYYVKqr8Ws+Tk9R6bo4cVXbgFWRJD2LIgezt1qECQQDF2AEUDv2pgWu+EM6F/bbymA/YuxyYebx2ZTUZHxd0+U8F2gFi5bm4QMMg6tKT3NgUVxjMuEq7/Ei3xpqs7Y3RAkEAgmT5u4kx1xFG47d6SNoA/d/XxVcUB0nb/aU8qjbA4d8CUi6sqz2G9AEQ96tCFpzA7Dx4awIvQ8JxYYG33CLXAQJAZW6ZHu+QnZb85jU3rPqnETlk5nkH8CR5uzmoP3jxTceD/DwI+etNjGmnoPjDfYt8PPFqhYr2xepSXeY05vPkAQJAM3X+VWg7n+Zoau6JMBn0q1z5No1/YzoSpQkTxSTZi/wSvO9nkC2PozjKRpSBm7QUVzCbTYEoO9MiIn0ec8qYpg==");
        return doPost("http://cvop.jd.com/component/http/JSON/youli/v1/order/getReprocessingOrderDetail",map);
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
//        signParamMap.put("jdOrderId", "300365480819");
        signParamMap.put("jdOrderId", "299743530133");
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
