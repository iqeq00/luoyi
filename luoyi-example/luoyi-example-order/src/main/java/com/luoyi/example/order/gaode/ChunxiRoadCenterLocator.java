package com.luoyi.example.order.gaode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.alibaba.fastjson2.JSONObject;

public class ChunxiRoadCenterLocator {
    // 替换为你的高德API密钥
    private static final String API_KEY = "a2f03ffe48daaf826ad4d79e5864b947";
    // 地理编码API地址
    private static final String GEOCODING_URL = "https://restapi.amap.com/v3/geocode/geo";

    public static void main(String[] args) {
        // 春熙路商业区的精确描述，有助于获取中心坐标
        String address = "成都市锦江区春熙路商圈中心";
        String city = "成都市"; // 限定城市，提高精度

        try {
            // 构建请求URL，对地址进行URL编码
            String encodedAddress = java.net.URLEncoder.encode(address, "UTF-8");
            String encodedCity = java.net.URLEncoder.encode(city, "UTF-8");
            String urlStr = String.format("%s?address=%s&city=%s&key=%s",
                    GEOCODING_URL, encodedAddress, encodedCity, API_KEY);

            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // 读取API响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            // 解析JSON响应
            JSONObject result = JSONObject.parseObject(response.toString());
            if ("1".equals(result.getString("status"))) {
                // 获取第一个匹配结果的中心点坐标
                JSONObject geocode = result.getJSONArray("geocodes").getJSONObject(0);
                String location = geocode.getString("location");
                String[] lngLat = location.split(",");

                System.out.println("春熙路中心经纬度：");
                System.out.println("经度：" + lngLat[0]);
                System.out.println("纬度：" + lngLat[1]);
            } else {
                System.out.println("查询失败：" + result.getString("info"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

