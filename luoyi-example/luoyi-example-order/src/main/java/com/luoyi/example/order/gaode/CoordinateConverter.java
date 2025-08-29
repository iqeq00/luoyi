package com.luoyi.example.order.gaode;

public class CoordinateConverter {
    // 圆周率相关常量
    private static final double X_PI = Math.PI * 3000.0 / 180.0;

    /**
     * 将百度BD09坐标转换为高德GCJ-02坐标
     * @param bdLon 百度经度
     * @param bdLat 百度纬度
     * @return 转换后的高德坐标，数组第一个元素是经度，第二个是纬度
     */
    public static double[] bd09ToGcj02(double bdLon, double bdLat) {
        double x = bdLon - 0.0065;
        double y = bdLat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * X_PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * X_PI);

        double gcjLon = z * Math.cos(theta);
        double gcjLat = z * Math.sin(theta);

        return new double[]{gcjLon, gcjLat};
    }

    // 使用示例
    public static void main(String[] args) {
        // 百度经纬度示例：北京天安门
        double bdLon = 104.10;
        double bdLat = 30.65;
        // 转换为高德经纬度
        double[] gcjCoords = bd09ToGcj02(bdLon, bdLat);

        System.out.println("百度经纬度: " + bdLon + ", " + bdLat);
        System.out.println("高德经纬度: " + gcjCoords[0] + ", " + gcjCoords[1]);
    }

}




