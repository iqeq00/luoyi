package com.luoyi.example.order.gaode1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubwayDataFetcher {
    private static final String AMAP_API_KEY = "a2f03ffe48daaf826ad4d79e5864b947";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // 城市地铁数据类
    static class CitySubwayData {
        private String city;
        private List<Line> lines;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public List<Line> getLines() {
            return lines;
        }

        public void setLines(List<Line> lines) {
            this.lines = lines;
        }
    }

    // 地铁线路类
    static class Line {
        private String lineName;
        private List<Station> stations;

        public String getLineName() {
            return lineName;
        }

        public void setLineName(String lineName) {
            this.lineName = lineName;
        }

        public List<Station> getStations() {
            return stations;
        }

        public void setStations(List<Station> stations) {
            this.stations = stations;
        }
    }

    // 地铁站类
    static class Station {
        private String stationName;
        private double latitude;
        private double longitude;

        public String getStationName() {
            return stationName;
        }

        public void setStationName(String stationName) {
            this.stationName = stationName;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }

    public static List<CitySubwayData> fetchNationalSubwayData() throws IOException {
        List<CitySubwayData> allCitySubwayData = new ArrayList<>();
        // 城市ID和名称列表，实际应用中可以扩展为完整的全国城市列表
        List<String> cityIds = List.of("010", "021", "020", "022");
        List<String> cityNames = List.of("北京", "上海", "广州", "天津");

        for (int i = 0; i < cityIds.size(); i++) {
            String cityId = cityIds.get(i);
            String cityName = cityNames.get(i);
            String url = "http://map.amap.com/service/subway?_1818387860087&srhdata=" + cityId + "_drw_" + cityName + ".json";

            try {
                // 使用RestTemplate发送GET请求
                ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

                if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                    CitySubwayData citySubwayData = objectMapper.readValue(response.getBody(), CitySubwayData.class);
                    citySubwayData.setCity(cityName);
                    allCitySubwayData.add(citySubwayData);
                } else {
                    System.err.println("获取" + cityName + "地铁数据失败，状态码：" + response.getStatusCodeValue());
                }
            } catch (Exception e) {
                System.err.println("获取" + cityName + "地铁数据时发生错误：" + e.getMessage());
                e.printStackTrace();
            }
        }

        return allCitySubwayData;
    }

    public static void main(String[] args) {
        try {
            List<CitySubwayData> nationalSubwayData = fetchNationalSubwayData();

            // 使用Java 8的Stream API处理和打印数据
            nationalSubwayData.forEach(cityData -> {
                System.out.println("城市：" + cityData.getCity());
                cityData.getLines().forEach(line -> {
                    System.out.println("  线路：" + line.getLineName());
                    line.getStations().forEach(station -> {
                        System.out.printf("    站点：%s，经纬度：(%.6f, %.6f)%n",
                                station.getStationName(),
                                station.getLatitude(),
                                station.getLongitude());
                    });
                });
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
