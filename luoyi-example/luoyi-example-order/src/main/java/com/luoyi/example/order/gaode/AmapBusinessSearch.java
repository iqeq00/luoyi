package com.luoyi.example.order.gaode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class AmapBusinessSearch {
    // 替换为你的高德地图API密钥
    private static final String API_KEY = "a2f03ffe48daaf826ad4d79e5864b947";
    // 高德地图POI检索API
    private static final String POI_SEARCH_API = "https://restapi.amap.com/v3/place/text";

    public static void main(String[] args) {
        String city = "成都";
        String keyword = "商圈";

        try {
            // 查询整个城市的商圈
            String cityResult = searchBusinessDistricts(city, keyword, 1, 200);
            System.out.println("===== " + city + " 主要商圈 =====");
            System.out.println(parseAmapResult(cityResult));

            // 示例：查询成都地铁1号线天府广场站附近的商圈
            System.out.println("\n===== 成都理工附近商圈 =====");
            String nearbyResult = searchNearbyBusiness("电子科大", "成都", 1000);
            System.out.println(parseAmapResult(nearbyResult));

        } catch (IOException e) {
            System.err.println("查询失败: " + e.getMessage());
        }
    }

    /**
     * 搜索指定城市的商圈
     * @param city 城市名
     * @param keyword 搜索关键词
     * @param page 页码
     * @param pageSize 每页条数
     * @return API返回结果
     */
    public static String searchBusinessDistricts(String city, String keyword, int page, int pageSize) throws IOException {
        String url = POI_SEARCH_API + "?" +
                "key=" + API_KEY +
                "&keywords=" + URLEncoder.encode(keyword, "UTF-8") +
                "&city=" + URLEncoder.encode(city, "UTF-8") +
                "&page=" + page +
                "&offset=" + pageSize +
                "&extensions=base";

        return sendGetRequest(url);
    }

    /**
     * 搜索指定地点附近的商圈
     * @param location 地点名称
     * @param city 城市
     * @param radius 搜索半径(米)
     * @return API返回结果
     */
    public static String searchNearbyBusiness(String location, String city, int radius) throws IOException {
        // 先将地点转换为经纬度
        String geoUrl = "https://restapi.amap.com/v3/geocode/geo?" +
                "key=" + API_KEY +
                "&address=" + URLEncoder.encode(location, "UTF-8") +
                "&city=" + URLEncoder.encode(city, "UTF-8");

        String geoResult = sendGetRequest(geoUrl);
        String locationCoord = extractAmapLocation(geoResult);

        if (locationCoord == null) {
            return "无法获取位置坐标";
        }

        // 搜索附近商圈
        String nearbyUrl = "https://restapi.amap.com/v3/place/around?" +
                "key=" + API_KEY +
                "&location=" + locationCoord +
                "&keywords=商圈" +
                "&radius=" + radius +
                "&offset=20";

        return sendGetRequest(nearbyUrl);
    }

    // 发送GET请求
    private static String sendGetRequest(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "UTF-8"));

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        conn.disconnect();

        return response.toString();
    }

    // 提取高德地图地理编码结果中的经纬度
    private static String extractAmapLocation(String json) {
        if (json.contains("\"status\":\"1\"") && json.contains("\"location\":\"")) {
            int locStart = json.indexOf("\"location\":\"") + 12;
            int locEnd = json.indexOf("\"", locStart);
            return json.substring(locStart, locEnd);
        }
        return null;
    }

    // 解析高德地图返回结果，提取商圈名称
    private static String parseAmapResult(String json) {
        StringBuilder sb = new StringBuilder();
        String[] results = json.split("\"name\":\"");

        for (int i = 1; i < results.length; i++) {
            String name = results[i].split("\"")[0];
            sb.append(i + "- ").append(name).append("\n");
        }

        return sb.length() > 0 ? sb.toString() : "未找到商圈信息";
    }
}

