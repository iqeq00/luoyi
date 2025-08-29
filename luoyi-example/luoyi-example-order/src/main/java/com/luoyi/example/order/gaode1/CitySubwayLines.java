package com.luoyi.example.order.gaode1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CitySubwayLines {
    // 高德API密钥，需要替换为你自己的
    private static final String API_KEY = "99dc62acea4e101526eb84070d5a98c1";
    // 高德地铁线路查询API地址
    private static final String SUBWAY_URL = "https://restapi.amap.com/v3/place/text";

    public static void main(String[] args) {
        // 要查询的城市
        String city = "成都";

        try {
            String result = queryCitySubwayLines(city);
            System.out.println(city + "地铁线路查询结果：");
            System.out.println(result);
        } catch (IOException e) {
            System.err.println("查询失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 查询指定城市的地铁线路
     * @param city 城市名称
     * @return API返回的JSON结果
     * @throws IOException 网络请求异常
     */
    public static String queryCitySubwayLines(String city) throws IOException {
        // 构建请求参数，关键词为"地铁线路"，在指定城市中搜索
        StringBuilder params = new StringBuilder();
        params.append("keywords=").append(URLEncoder.encode("地铁线路", StandardCharsets.UTF_8.name()));
        params.append("&city=").append(URLEncoder.encode(city, StandardCharsets.UTF_8.name()));
        params.append("&type=490000"); // 490000表示交通设施-轨道交通相关
        params.append("&offset=20"); // 每页返回结果数
        params.append("&page=1"); // 页码
        params.append("&output=json"); // 返回格式
        params.append("&key=").append(API_KEY);

        // 创建URL对象
        URL url = new URL(SUBWAY_URL + "?" + params.toString());

        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        // 获取响应
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // 读取响应内容
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                return response.toString();
            }
        } else {
            throw new IOException("HTTP请求失败，响应码：" + responseCode);
        }
    }
}

