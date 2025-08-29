package com.luoyi.example.order.gaode1;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

// 地铁站点类
class SubwayStation {
    private String name;
    private double longitude;
    private double latitude;

    public SubwayStation(String name, double longitude, double latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "[" + name + " (" + longitude + ", " + latitude + ")]";
    }

    // Getters
    public String getName() { return name; }
    public double getLongitude() { return longitude; }
    public double getLatitude() { return latitude; }
}

// 地铁线路类
class SubwayLine {
    private String name;
    private List<SubwayStation> stations;

    public SubwayLine(String name) {
        this.name = name;
        this.stations = new ArrayList<>();
    }

    public void addStation(SubwayStation station) {
        stations.add(station);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(":\n");
        for (SubwayStation station : stations) {
            sb.append("  ").append(station).append("\n");
        }
        return sb.toString();
    }

    // Getters
    public String getName() { return name; }
    public List<SubwayStation> getStations() { return stations; }
}

public class SubwayLinesWithStations {
    // 高德API密钥，需要替换为你自己的
    private static final String API_KEY = "99dc62acea4e101526eb84070d5a98c1";
    // 高德地铁线路查询API
    private static final String SUBWAY_LINE_URL = "https://restapi.amap.com/v3/place/text";
    // 高德地铁站点查询API
    private static final String SUBWAY_STATION_URL = "https://restapi.amap.com/v3/place/around";

    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        String city = "成都"; // 可以替换为其他城市

        try {
            // 获取城市所有地铁线路
            List<SubwayLine> subwayLines = getCitySubwayLines(city);

            // 打印结果
            System.out.println(city + "地铁线路及站点信息：");
            for (SubwayLine line : subwayLines) {
                System.out.println(line);
            }

            // 此时subwayLines就是所需的二维数据结构
            // 第一维：subwayLines列表中的每个元素是一条地铁线
            // 第二维：每条线的getStations()方法返回该线路的所有站点，包含坐标
        } catch (IOException e) {
            System.err.println("查询失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 获取指定城市的所有地铁线路
     */
    public static List<SubwayLine> getCitySubwayLines(String city) throws IOException {
        List<SubwayLine> lines = new ArrayList<>();

        // 1. 首先查询该城市的所有地铁线路
        String lineResult = querySubwayLines(city);
        JsonObject lineJson = gson.fromJson(lineResult, JsonObject.class);

        // 检查返回状态
        if (!"1".equals(lineJson.get("status").getAsString())) {
            throw new IOException("获取地铁线路失败：" + lineJson.get("info").getAsString());
        }

        // 解析线路数据
        JsonArray pois = lineJson.getAsJsonArray("pois");
        for (JsonElement poi : pois) {
            JsonObject lineObj = poi.getAsJsonObject();
            String lineName = lineObj.get("name").getAsString();

            // 只处理包含"线"字的结果，过滤掉其他类型
            if (lineName.contains("线")) {
                SubwayLine line = new SubwayLine(lineName);

                // 获取线路中心点坐标，用于查询周边站点
                String location = lineObj.get("location").getAsString();
                String[] coords = location.split(",");
                double longitude = Double.parseDouble(coords[0]);
                double latitude = Double.parseDouble(coords[1]);

                // 2. 查询该线路的所有站点
                List<SubwayStation> stations = getSubwayStations(longitude, latitude, city);
                for (SubwayStation station : stations) {
                    line.addStation(station);
                }

                lines.add(line);
            }
        }

        return lines;
    }

    /**
     * 查询指定线路附近的所有地铁站点
     */
    private static List<SubwayStation> getSubwayStations(double longitude, double latitude, String city) throws IOException {
        List<SubwayStation> stations = new ArrayList<>();

        // 构建请求参数
        StringBuilder params = new StringBuilder();
        params.append("location=").append(longitude).append(",").append(latitude);
        params.append("&keywords=").append(URLEncoder.encode("地铁站", StandardCharsets.UTF_8.name()));
        params.append("&city=").append(URLEncoder.encode(city, StandardCharsets.UTF_8.name()));
        params.append("&radius=2000"); // 搜索半径，单位：米
        params.append("&type=490003"); // 490003表示地铁站
        params.append("&offset=50"); // 每页返回结果数
        params.append("&page=1"); // 页码
        params.append("&output=json");
        params.append("&key=").append(API_KEY);

        // 发送请求
        URL url = new URL(SUBWAY_STATION_URL + "?" + params.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        // 处理响应
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // 解析站点数据
                JsonObject stationJson = gson.fromJson(response.toString(), JsonObject.class);
                if ("1".equals(stationJson.get("status").getAsString())) {
                    JsonArray pois = stationJson.getAsJsonArray("pois");
                    for (JsonElement poi : pois) {
                        JsonObject stationObj = poi.getAsJsonObject();
                        String stationName = stationObj.get("name").getAsString();
                        String location = stationObj.get("location").getAsString();

                        String[] coords = location.split(",");
                        if (coords.length == 2) {
                            double stationLon = Double.parseDouble(coords[0]);
                            double stationLat = Double.parseDouble(coords[1]);
                            stations.add(new SubwayStation(stationName, stationLon, stationLat));
                        }
                    }
                }
            }
        } else {
            throw new IOException("获取地铁站点失败，响应码：" + responseCode);
        }

        return stations;
    }

    /**
     * 查询指定城市的地铁线路列表
     */
    private static String querySubwayLines(String city) throws IOException {
        StringBuilder params = new StringBuilder();
        params.append("keywords=").append(URLEncoder.encode("地铁线路", StandardCharsets.UTF_8.name()));
        params.append("&city=").append(URLEncoder.encode(city, StandardCharsets.UTF_8.name()));
        params.append("&type=490000"); // 交通设施-轨道交通相关
        params.append("&offset=30"); // 最多返回30条线路
        params.append("&page=1");
        params.append("&output=json");
        params.append("&key=").append(API_KEY);

        URL url = new URL(SUBWAY_LINE_URL + "?" + params.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
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
