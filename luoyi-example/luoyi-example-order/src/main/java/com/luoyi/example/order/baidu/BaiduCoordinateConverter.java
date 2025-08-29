package com.luoyi.example.order.baidu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

/**
 * 百度地图坐标转换工具类
 */
public class BaiduCoordinateConverter {
    // 百度地图API密钥，需要替换为你自己的AK
    private static final String BAIDU_API_KEY = "Aaayv1vLNcl4ZzyxktThBIVGRRq9yjqk";

    /**
     * 百度墨卡托坐标(BD09MC)转百度经纬度(BD09LL)
     * @param x 墨卡托X坐标
     * @param y 墨卡托Y坐标
     * @return 经纬度数组 [经度, 纬度]
     */
    public static double[] bd09mcToBd09ll(double x, double y) {
        double[] result = new double[2];
        double xVal = x / 1000000.0;
        double yVal = y / 1000000.0;

        double lng = 0.0;
        double lat = 0.0;

        // 墨卡托转经纬度公式
        lng = xVal - 0.0065;
        lng = lng - 0.006;
        lat = yVal - 0.006;
        lat = lat - 0.002;

        // 进一步精确计算
        double z = Math.sqrt(xVal * xVal + yVal * yVal) + 0.00002 * Math.sin(yVal * Math.PI);
        double theta = Math.atan2(yVal, xVal) + 0.000003 * Math.cos(xVal * Math.PI);

        lng = z * Math.cos(theta) + 0.0065;
        lat = z * Math.sin(theta) + 0.006;

        result[0] = lng;
        result[1] = lat;

        return result;
    }

    /**
     * 其他坐标系转百度经纬度(BD09LL)
     * @param lon 原始经度
     * @param lat 原始纬度
     * @param fromSys 原始坐标系：1-WGS84, 2-GCJ02, 3-火星坐标系(高德)
     * @return 经纬度数组 [经度, 纬度]，转换失败返回null
     */
    public static double[] convertToBd09ll(double lon, double lat, int fromSys) {
        try {
            // 构造API请求URL
            String url = String.format(
                    "http://api.map.baidu.com/geoconv/v1/?coords=%f,%f&from=%d&to=5&ak=%s",
                    lon, lat, fromSys, BAIDU_API_KEY
            );

            // 发送HTTP请求
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            // 读取响应
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            // 解析JSON响应
            JSONObject json = JSONObject.parseObject(response.toString());
            if (json.getIntValue("status") == 0) {
                JSONArray result = json.getJSONArray("result");
                if (result.size() > 0) {
                    JSONObject coords = result.getJSONObject(0);
                    return new double[] {
                            coords.getDoubleValue("x"),
                            coords.getDoubleValue("y")
                    };
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 使用示例
    public static void main(String[] args) {
        // 1. 百度墨卡托坐标转百度经纬度示例
        double x = 11575655.91;
        double y = 3570542.78;
        double[] bd09ll = bd09mcToBd09ll(x, y);
        System.out.printf("墨卡托转经纬度: 经度=%.6f, 纬度=%.6f%n", bd09ll[0], bd09ll[1]);

        // 2. 其他坐标系转百度经纬度示例(WGS84转BD09LL)
        double[] converted = convertToBd09ll(116.317854, 40.05161, 1);
        if (converted != null) {
            System.out.printf("转换后百度经纬度: 经度=%.6f, 纬度=%.6f%n", converted[0], converted[1]);
        } else {
            System.out.println("坐标转换失败");
        }
    }
}

