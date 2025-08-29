package com.luoyi.example.order.gaode1;

import com.luoyi.example.order.utils.HttpUtils;

import java.util.HashMap;

/**
 * 查询省市区
 */
public class MainTest01 {

    public static void main(String[] args) {
        String url = "https://restapi.amap.com/v3/config/district";
        url = url + "?keywords=成都&key=99dc62acea4e101526eb84070d5a98c1";
        String res = HttpUtils.get(url, new HashMap<>());
        System.out.println(res);
    }
}
