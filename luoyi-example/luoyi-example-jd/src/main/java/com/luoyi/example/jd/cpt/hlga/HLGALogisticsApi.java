package com.luoyi.example.jd.cpt.hlga;

import cn.hutool.core.map.MapUtil;
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
 * 和乐关爱锦礼 调用 京东vop接口 - 7.10 查询配送信息
 */
public class HLGALogisticsApi {

    private static final Logger log = LoggerFactory.getLogger(HLGALogisticsApi.class);

    static String logisticsUrl = "https://bizapi.jd.com/api/order/orderTrack";
    /**
     * 通过订单号查询物流信息
     */
    public static void getLogistics(String jdOrderId) {

        HashMap<String, Object> imap = MapUtil.newHashMap(1 << 4);
        imap.put("token", "wAi3AlguCHAyxbphCeRaP2CgP");
        imap.put("jdOrderId", jdOrderId);
        imap.put("waybillCode", "1");

        String res = doPost(logisticsUrl, imap);
        log.info("获取配送信息返回结果={}", res);
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
            log.error("调用京东api异常,url:{},参数:{},异常:{}", url, e);
        }
        return result;
    }

    public static void main(String[] args) {
        String jdOrderId = "300547680121";
        getLogistics(jdOrderId);
    }
}
