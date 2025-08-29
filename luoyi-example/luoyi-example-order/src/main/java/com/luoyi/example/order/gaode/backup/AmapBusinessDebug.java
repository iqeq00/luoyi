package com.luoyi.example.order.gaode.backup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

class BusinessDistrict {
    private String name;
    private String location; // 经纬度：经度,纬度
    private String address;

    public BusinessDistrict(String name, String location, String address) {
        this.name = name;
        this.location = location;
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("名称: %s\n地址: %s\n经纬度: %s\n",
                name, address, location);
    }
}

public class AmapBusinessDebug {
    // 替换为你的高德API密钥
    private static final String API_KEY = "a2f03ffe48daaf826ad4d79e5864b947";
    private static final String POI_SEARCH_API = "https://restapi.amap.com/v3/place/text";

    public static void main(String[] args) {
        String city = "成都";
        String keyword = "商圈";

        try {
            System.out.println("开始查询 [" + city + "] 的 [" + keyword + "]...");
            System.out.println("使用的API密钥：" + (API_KEY.contains("你的") ? "未配置（请替换为实际密钥）" : "已配置"));

            List<BusinessDistrict> districts = searchBusinessDistrictsWithCoords(city, keyword, 1, 20);

            if (districts.isEmpty()) {
                System.out.println("\n未查询到结果，请检查：");
                System.out.println("1. API密钥是否有效");
                System.out.println("2. 网络连接是否正常");
                System.out.println("3. 尝试更换关键词（如\"商业区\"、\"购物中心\"）");
            } else {
                System.out.println("\n===== 共查询到 " + districts.size() + " 个结果 =====");
                for (BusinessDistrict district : districts) {
                    System.out.println(district);
                }
            }

        } catch (IOException e) {
            System.err.println("\n查询过程出错：" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<BusinessDistrict> searchBusinessDistrictsWithCoords(
            String city, String keyword, int page, int pageSize) throws IOException {

        List<BusinessDistrict> resultList = new ArrayList<>();

        // 构建完整请求URL（便于调试）
        String url = POI_SEARCH_API + "?" +
                "key=" + API_KEY +
                "&keywords=" + URLEncoder.encode(keyword, "UTF-8") +
                "&city=" + URLEncoder.encode(city, "UTF-8") +
                "&page=" + page +
                "&offset=" + pageSize +
                "&extensions=base";

        System.out.println("\n请求的API地址：" + url);

        String response = sendGetRequest(url);
        System.out.println("API返回原始数据：" + (response.length() > 500 ? response.substring(0, 500) + "..." : response));

        resultList = parseAmapResultWithCoords(response);
        return resultList;
    }

    private static String sendGetRequest(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(10000); // 延长超时时间
        conn.setReadTimeout(10000);

        // 打印响应状态码（便于调试）
        System.out.println("HTTP响应状态码：" + conn.getResponseCode());

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

    private static List<BusinessDistrict> parseAmapResultWithCoords(String json) {
        List<BusinessDistrict> districts = new ArrayList<>();

        // 检查返回状态
        if (!json.contains("\"status\":\"1\"")) {
            System.out.println("API返回状态异常：" + json);
            return districts;
        }

        // 解析POI数据
        if (!json.contains("\"pois\":")) {
            System.out.println("未包含商圈数据");
            return districts;
        }

        String[] pois = json.split("\"pois\":\\[")[1].split("\\]")[0].split("\\},\\{");

        for (String poi : pois) {
            String cleanPoi = poi.replace("{", "").replace("}", "");
            String name = extractField(cleanPoi, "name");
            String location = extractField(cleanPoi, "location");
            String address = extractField(cleanPoi, "address");

            if (name != null && location != null) {
                districts.add(new BusinessDistrict(name, location, address != null ? address : "地址未知"));
            }
        }

        return districts;
    }

    private static String extractField(String poiStr, String fieldName) {
        String searchStr = "\"" + fieldName + "\":\"";
        if (poiStr.contains(searchStr)) {
            int start = poiStr.indexOf(searchStr) + searchStr.length();
            int end = poiStr.indexOf("\"", start);
            if (end > start) {
                return poiStr.substring(start, end);
            }
        }
        return null;
    }
}

