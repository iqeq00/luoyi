//package com.luoyi.example.order.gaode;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.List;
//
//// 商圈信息实体类
//class BusinessDistrict {
//    private String name;
//    private String location; // 经纬度，格式: 经度,纬度
//    private String address;
//
//    public BusinessDistrict(String name, String location, String address) {
//        this.name = name;
//        this.location = location;
//        this.address = address;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("名称: %s\n地址: %s\n经纬度: %s\n",
//                name, address, location);
//    }
//
//    // getter方法
//    public String getName() { return name; }
//    public String getLocation() { return location; }
//    public String getAddress() { return address; }
//}
//
//public class AmapBusinessWithCoordinates {
//    // 替换为你的高德地图API密钥
//    private static final String API_KEY = "你的高德地图API密钥";
//    // 高德地图POI检索API
//    private static final String POI_SEARCH_API = "https://restapi.amap.com/v3/place/text";
//
//    public static void main(String[] args) {
//        String city = "成都";
//        String keyword = "商圈";
//
//        try {
//            // 查询整个城市的商圈（带经纬度）
//            List<BusinessDistrict> districts = searchBusinessDistrictsWithCoords(city, keyword, 1, 20);
//
//            System.out.println("===== " + city + " 主要商圈及经纬度 =====");
//            for (BusinessDistrict district : districts) {
//                System.out.println(district);
//            }
//
//        } catch (IOException e) {
//            System.err.println("查询失败: " + e.getMessage());
//        }
//    }
//
//    /**
//     * 搜索指定城市的商圈并获取经纬度
//     * @param city 城市名
//     * @param keyword 搜索关键词
//     * @param page 页码
//     * @param pageSize 每页条数
//     * @return 商圈列表，包含名称、地址和经纬度
//     */
//    public static List<BusinessDistrict> searchBusinessDistrictsWithCoords(
//            String city, String keyword, int page, int pageSize) throws IOException {
//
//        List<BusinessDistrict> resultList = new ArrayList<>();
//
//        String url = POI_SEARCH_API + "?" +
//                "key=" + API_KEY +
//                "&keywords=" + URLEncoder.encode(keyword, "UTF-8") +
//                "&city=" + URLEncoder.encode(city, "UTF-8") +
//                "&page=" + page +
//                "&offset=" + pageSize +
//                "&extensions=base";
//
//        String response = sendGetRequest(url);
//        resultList = parseAmapResultWithCoords(response);
//
//        return resultList;
//    }
//
//    /**
//     * 搜索指定地点附近的商圈并获取经纬度
//     * @param location 地点名称
//     * @param city 城市
//     * @param radius 搜索半径(米)
//     * @return 商圈列表，包含名称、地址和经纬度
//     */
//    public static List<BusinessDistrict> searchNearbyWithCoords(
//            String location, String city, int radius) throws IOException {
//
//        // 先将地点转换为经纬度
//        String geoUrl = "https://restapi.amap.com/v3/geocode/geo?" +
//                "key=" + API_KEY +
//                "&address=" + URLEncoder.encode(location, "UTF-8") +
//                "&city=" + URLEncoder.encode(city, "UTF-8");
//
//        String geoResult = sendGetRequest(geoUrl);
//        String locationCoord = extractAmapLocation(geoResult);
//
//        if (locationCoord == null) {
//            return new ArrayList<>();
//        }
//
//        // 搜索附近商圈
//        String nearbyUrl = "https://restapi.amap.com/v3/place/around?" +
//                "key=" + API_KEY +
//                "&location=" + locationCoord +
//                "&keywords=商圈" +
//                "&radius=" + radius +
//                "&offset=20";
//
//        String response = sendGetRequest(nearbyUrl);
//        return parseAmapResultWithCoords(response);
//    }
//
//    // 发送GET请求
//    private static String sendGetRequest(String urlStr) throws IOException {
//        URL url = new URL(urlStr);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setConnectTimeout(5000);
//        conn.setReadTimeout(5000);
//
//        BufferedReader reader = new BufferedReader(
//                new InputStreamReader(conn.getInputStream(), "UTF-8"));
//
//        StringBuilder response = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            response.append(line);
//        }
//        reader.close();
//        conn.disconnect();
//
//        return response.toString();
//    }
//
//    // 提取高德地图地理编码结果中的经纬度
//    private static String extractAmapLocation(String json) {
//        if (json.contains("\"status\":\"1\"") && json.contains("\"location\":\"")) {
//            int locStart = json.indexOf("\"location\":\"") + 12;
//            int locEnd = json.indexOf("\"", locStart);
//            return json.substring(locStart, locEnd);
//        }
//        return null;
//    }
//
//    // 解析高德地图返回结果，提取商圈名称、地址和经纬度
//    private static List<BusinessDistrict> parseAmapResultWithCoords(String json) {
//        List<BusinessDistrict> districts = new ArrayList<>();
//
//        // 检查返回状态是否成功
//        if (!json.contains("\"status\":\"1\"")) {
//            return districts;
//        }
//
//        // 分割结果获取每个POI条目
//        String[] pois = json.split("\"pois\":\\[")[1].split("\\]")[0].split("\\},\\{");
//
//        for (String poi : pois) {
//            // 清理POI字符串
//            String cleanPoi = poi.replace("{", "").replace("}", "");
//
//            // 提取名称
//            String name = extractField(cleanPoi, "name");
//            // 提取经纬度
//            String location = extractField(cleanPoi, "location");
//            // 提取地址
//            String address = extractField(cleanPoi, "address");
//
//            if (name != null && location != null) {
//                districts.add(new BusinessDistrict(name, location, address != null ? address : "地址未知"));
//            }
//        }
//
//        return districts;
//    }
//
//    // 从POI字符串中提取指定字段的值
//    private static String extractField(String poiStr, String fieldName) {
//        String searchStr = "\"" + fieldName + "\":\"";
//        if (poiStr.contains(searchStr)) {
//            int start = poiStr.indexOf(searchStr) + searchStr.length();
//            int end = poiStr.indexOf("\"", start);
//            if (end > start) {
//                return poiStr.substring(start, end);
//            }
//        }
//        return null;
//    }
//}
//
