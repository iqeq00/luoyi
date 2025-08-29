package com.luoyi.example.order.gaode.backup;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.alibaba.fastjson2.JSONObject;

public class AmapGeocoder {
    // 高德地图API密钥，需要替换为你自己的
    private static final String API_KEY = "a2f03ffe48daaf826ad4d79e5864b947";
    // 地理编码API地址
    private static final String GEOCODING_URL = "https://restapi.amap.com/v3/geocode/geo";

    public static void main(String[] args) {
        String address = "成都市春熙路";
        String city = "成都市"; // 城市参数，提高查询准确性

        try {
            // 构建请求URL
            String urlStr = GEOCODING_URL + "?address=" +
                    java.net.URLEncoder.encode(address, "UTF-8") +
                    "&city=" + java.net.URLEncoder.encode(city, "UTF-8") +
                    "&key=" + API_KEY;

            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // 读取响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            // 解析JSON响应
            JSONObject jsonObject = JSONObject.parseObject(response.toString());
            String status = jsonObject.getString("status");

            if ("1".equals(status)) {
                // 获取第一个结果的经纬度
                String geocodes = jsonObject.getJSONArray("geocodes").getString(0);
                JSONObject geocode = JSONObject.parseObject(geocodes);
                String location = geocode.getString("location");

                if (location != null && !location.isEmpty()) {
                    String[] lngLat = location.split(",");
                    System.out.println("春熙路经纬度:");
                    System.out.println("经度: " + lngLat[0]);
                    System.out.println("纬度: " + lngLat[1]);
                }
            } else {
                System.out.println("查询失败，错误信息: " + jsonObject.getString("info"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

