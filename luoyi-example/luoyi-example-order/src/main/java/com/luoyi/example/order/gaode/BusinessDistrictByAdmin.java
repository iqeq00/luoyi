package com.luoyi.example.order.gaode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

// 商圈信息模型
class BusinessCircle {
    private String name;          // 商圈名称
    private String location;      // 经纬度 (经度,纬度)
    private String address;       // 详细地址
    private String adcode;        // 行政区划代码

    public BusinessCircle(String name, String location, String address, String adcode) {
        this.name = name;
        this.location = location;
        this.address = address;
        this.adcode = adcode;
    }

    // getter方法
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getAddress() { return address; }
    public String getAdcode() { return adcode; }

    @Override
    public String toString() {
        return String.format("  商圈名称: %s\n  地址: %s\n  经纬度: %s\n",
                name, address, location);
    }
}

// 行政区信息模型
class AdministrativeArea {
    private String name;                // 行政区名称
    private String adcode;              // 行政区划代码
    private List<BusinessCircle> businessCircles;  // 下属商圈列表

    public AdministrativeArea(String name, String adcode) {
        this.name = name;
        this.adcode = adcode;
        this.businessCircles = new ArrayList<>();
    }

    // 添加商圈
    public void addBusinessCircle(BusinessCircle circle) {
        this.businessCircles.add(circle);
    }

    // getter方法
    public String getName() { return name; }
    public String getAdcode() { return adcode; }
    public List<BusinessCircle> getBusinessCircles() { return businessCircles; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("行政区: ").append(name).append(" (").append(adcode).append(")\n");
        sb.append("包含商圈: ").append(businessCircles.size()).append("个\n");
        for (BusinessCircle circle : businessCircles) {
            sb.append(circle.toString());
        }
        return sb.toString();
    }
}

public class BusinessDistrictByAdmin {
    // 高德地图API密钥 - 请替换为你的实际密钥
    private static final String API_KEY = "a2f03ffe48daaf826ad4d79e5864b947";

    // 高德地图API接口
    private static final String DISTRICT_API = "https://restapi.amap.com/v3/config/district";
    private static final String POI_SEARCH_API = "https://restapi.amap.com/v3/place/text";

    public static void main(String[] args) {
        String city = "成都";  // 要查询的城市

        try {
            // 1. 获取城市的所有行政区
            List<AdministrativeArea> adminAreas = getAdministrativeAreas(city);
            System.out.println("获取到 " + adminAreas.size() + " 个行政区\n");

            // 2. 为每个行政区查询商圈
            for (AdministrativeArea area : adminAreas) {
                System.out.println("正在查询 " + area.getName() + " 的商圈...");
                List<BusinessCircle> circles = searchBusinessCirclesInArea(area.getName(), city);
                for (BusinessCircle circle : circles) {
                    area.addBusinessCircle(circle);
                }
                System.out.println("  已找到 " + circles.size() + " 个商圈\n");
            }

            // 3. 输出整理后的结果
            System.out.println("===== 最终查询结果 =====");
            for (AdministrativeArea area : adminAreas) {
                if (!area.getBusinessCircles().isEmpty()) {
                    System.out.println(area);
                }
            }

        } catch (IOException e) {
            System.err.println("查询失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 获取指定城市的所有行政区
     */
    private static List<AdministrativeArea> getAdministrativeAreas(String city) throws IOException {
        List<AdministrativeArea> areas = new ArrayList<>();

        // 构建请求URL
        String url = DISTRICT_API + "?" +
                "key=" + API_KEY +
                "&keywords=" + URLEncoder.encode(city, "UTF-8") +
                "&subdistrict=1" +  // 获取下一级行政区
                "&extensions=base";

        // 发送请求
        String response = sendGetRequest(url);

        // 解析行政区数据
        if (response.contains("\"districts\":")) {
            String districtsStr = response.split("\"districts\":\\[")[1].split("\\]")[0];
            String[] districtItems = districtsStr.split("\\},\\{");

            for (String item : districtItems) {
                String cleanItem = item.replace("{", "").replace("}", "");
                String name = extractField(cleanItem, "name");
                String adcode = extractField(cleanItem, "adcode");

                // 只获取区级行政区（根据实际情况调整过滤条件）
                if (name != null && adcode != null &&
                        (name.endsWith("区") || name.endsWith("县") || name.endsWith("市"))) {
                    areas.add(new AdministrativeArea(name, adcode));
                }
            }
        }

        return areas;
    }

    /**
     * 查询指定行政区内的商圈
     */
    private static List<BusinessCircle> searchBusinessCirclesInArea(String district, String city) throws IOException {
        List<BusinessCircle> circles = new ArrayList<>();
        int page = 1;
        final int pageSize = 20;  // 每页最大条数

        while (true) {
            // 构建请求URL
            String url = POI_SEARCH_API + "?" +
                    "key=" + API_KEY +
                    "&keywords=" + URLEncoder.encode("商圈", "UTF-8") +
                    "&city=" + URLEncoder.encode(city, "UTF-8") +
                    "&district=" + URLEncoder.encode(district, "UTF-8") +
                    "&page=" + page +
                    "&offset=" + pageSize +
                    "&extensions=base";

            // 发送请求
            String response = sendGetRequest(url);

            // 检查是否请求成功
            if (!response.contains("\"status\":\"1\"")) {
                break;
            }

            // 解析商圈数据
            List<BusinessCircle> pageCircles = parseBusinessCircles(response);
            if (pageCircles.isEmpty()) {
                break;  // 没有更多数据
            }

            circles.addAll(pageCircles);

            // 检查是否还有下一页
            if (pageCircles.size() < pageSize) {
                break;
            }

            page++;
        }

        return circles;
    }

    /**
     * 解析API返回的商圈数据
     */
    private static List<BusinessCircle> parseBusinessCircles(String json) {
        List<BusinessCircle> circles = new ArrayList<>();

        if (!json.contains("\"pois\":")) {
            return circles;
        }

        String poisStr = json.split("\"pois\":\\[")[1].split("\\]")[0];
        String[] poiItems = poisStr.split("\\},\\{");

        for (String item : poiItems) {
            String cleanItem = item.replace("{", "").replace("}", "");
            String name = extractField(cleanItem, "name");
            String location = extractField(cleanItem, "location");
            String address = extractField(cleanItem, "address");
            String adcode = extractField(cleanItem, "adcode");

            if (name != null && location != null) {
                circles.add(new BusinessCircle(name, location,
                        address != null ? address : "地址未知",
                        adcode != null ? adcode : "未知编码"));
            }
        }

        return circles;
    }

    /**
     * 发送GET请求
     */
    private static String sendGetRequest(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);

        // 检查响应状态
        if (conn.getResponseCode() != 200) {
            throw new IOException("HTTP请求失败，状态码: " + conn.getResponseCode());
        }

        // 读取响应内容
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

    /**
     * 从JSON字符串中提取指定字段的值
     */
    private static String extractField(String jsonStr, String fieldName) {
        String searchStr = "\"" + fieldName + "\":\"";
        if (jsonStr.contains(searchStr)) {
            int start = jsonStr.indexOf(searchStr) + searchStr.length();
            int end = jsonStr.indexOf("\"", start);
            if (end > start) {
                return jsonStr.substring(start, end);
            }
        }
        return null;
    }
}

