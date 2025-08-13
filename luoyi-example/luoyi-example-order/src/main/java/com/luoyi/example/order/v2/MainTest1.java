package com.luoyi.example.order.v2;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;

public class MainTest1 {

    public static void main(String[] args) throws Exception {
        // 1. 目标URL（支付回调接口）
        String url = "https://gw.ygfuli.com/platform/open/wly/order/pay/call";

        // 2. 准备JSON参数（注意转义嵌套JSON）
        String jsonParams = "{"
                + "\"data\":\"{\\\"cardAmount\\\":208,\\\"amount\\\":208,"
                + "\\\"orderNumber\\\":\\\"20FF7A6594A2ECA654F5E7BB605384\\\","
                + "\\\"orderStatus\\\":1,\\\"cardNo\\\":\\\"8333028011235276468,\\\","
                + "\\\"merchantNumber\\\":\\\"1\\\",\\\"otherAmount\\\":null,"
                + "\\\"ticketAmount\\\":0,\\\"payType\\\":0,\\\"phone\\\":\\\"18113104916\\\","
                + "\\\"merchantId\\\":\\\"1\\\",\\\"orderDesc\\\":\\\"交易成功\\\","
                + "\\\"ticketName\\\":null}\","
                + "\"sign\":\"304402201e31ca099aea0ca990b62db23cba28d984e6c88d78054aea839dcf90fd3626fe022067502e631500f3e046cdc8b3c86062936689ee09cf69b65152bc151a6d6c8e24\""
                + "}";

        // 3. 创建绕过SSL验证的HttpClient
        CloseableHttpClient httpClient = createUnsafeHttpClient();

        try {
            // 4. 创建POST请求
            HttpPost httpPost = new HttpPost(url);

            // 5. 设置请求头和JSON参数
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(jsonParams, "UTF-8"));

            // 6. 发送请求并获取响应
            HttpResponse response = httpClient.execute(httpPost);

            // 7. 输出结果
            System.out.println("响应状态码: " + response.getStatusLine().getStatusCode());
            System.out.println("响应内容: " + EntityUtils.toString(response.getEntity()));

        } finally {
            httpClient.close();
        }
    }

    /**
     * 创建跳过SSL验证的HttpClient（不安全！仅用于测试）
     */
    private static CloseableHttpClient createUnsafeHttpClient() throws Exception {
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(null, new TrustStrategy() {
                    @Override
                    public boolean isTrusted(X509Certificate[] chain, String authType) {
                        return true; // 信任所有证书
                    }
                }).build();

        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
                sslContext,
                NoopHostnameVerifier.INSTANCE // 不验证主机名
        );

        return HttpClients.custom()
                .setSSLSocketFactory(socketFactory)
                .build();
    }
}