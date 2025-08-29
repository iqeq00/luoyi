package com.luoyi.example.order.baidu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class BusinessDistrictSearch {
    // 替换为你的百度地图API密钥
    private static final String API_KEY = "Aaayv1vLNcl4ZzyxktThBIVGRRq9yjqk";
    // 百度地图POI检索API
    private static final String SEARCH_API = "https://api.map.baidu.com/place/v2/search";

    public static void main(String[] args) {
        // 成都地铁1号线主要站点
        String[] line1Stations = {
                "升仙湖", "火车北站", "人民北路", "文殊院", "骡马市",
                "天府广场", "锦江宾馆", "华西坝", "省体育馆",
                "倪家桥", "桐梓林", "火车南站", "高新", "金融城",
                "孵化园", "世纪城", "锦城广场", "孵化园", "海昌路", "广福", "红石公园", "麓湖", "武汉路", "天府公园", "西博城", "广州路", "兴隆湖"
        };

        // 查询每个站点附近的商圈
        for (String station : line1Stations) {
            try {
                System.out.println("\n===== " + station + " 附近商圈 =====");
                String result = searchNearby(station, "成都", "商圈", 1000); // 1000米范围内
                System.out.println(parseSimpleResult(result));
            } catch (IOException e) {
                System.err.println(station + "查询失败: " + e.getMessage());
            }
        }
    }

    /**
     * 搜索指定地点附近的商圈
     * @param location 位置名称
     * @param city 城市
     * @param keyword 关键词
     * @param radius 搜索半径(米)
     * @return API返回结果
     */
    public static String searchNearby(String location, String city, String keyword, int radius) throws IOException {
        // 先将地点转换为经纬度
        String geocoderUrl = "https://api.map.baidu.com/geocoding/v3/?" +
                "address=" + URLEncoder.encode(location, "UTF-8") +
                "&city=" + URLEncoder.encode(city, "UTF-8") +
                "&output=json" +
                "&ak=" + API_KEY;

        URL url = new URL(geocoderUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder geoResult = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            geoResult.append(line);
        }
        reader.close();
        conn.disconnect();

        // 提取经纬度
        String locationCoord = extractLocation(geoResult.toString());
        if (locationCoord == null) {
            return "无法获取位置坐标";
        }

        // 基于经纬度搜索附近商圈
        String searchUrl = SEARCH_API + "?" +
                "query=" + URLEncoder.encode(keyword, "UTF-8") +
                "&location=" + locationCoord +
                "&radius=" + radius +
                "&output=json" +
                "&ak=" + API_KEY;

        URL searchUrlObj = new URL(searchUrl);
        HttpURLConnection searchConn = (HttpURLConnection) searchUrlObj.openConnection();
        searchConn.setRequestMethod("GET");

        BufferedReader searchReader = new BufferedReader(new InputStreamReader(searchConn.getInputStream(), "UTF-8"));
        StringBuilder searchResult = new StringBuilder();
        while ((line = searchReader.readLine()) != null) {
            searchResult.append(line);
        }
        searchReader.close();
        searchConn.disconnect();

        return searchResult.toString();
    }

    // 从地理编码结果中提取经纬度
    private static String extractLocation(String json) {
        if (json.contains("\"status\":0") && json.contains("\"location\":")) {
            int locStart = json.indexOf("\"location\":") + 11;
            int locEnd = json.indexOf("}", locStart);
            return json.substring(locStart, locEnd);
        }
        return null;
    }

    // 简单解析结果，提取商圈名称
    private static String parseSimpleResult(String json) {
        StringBuilder sb = new StringBuilder();
        String[] results = json.split("\"name\":\"");

        for (int i = 1; i < results.length; i++) {
            String name = results[i].split("\"")[0];
            sb.append("- ").append(name).append("\n");
        }

        return sb.length() > 0 ? sb.toString() : "未找到商圈信息";
    }
}


