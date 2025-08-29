package com.luoyi.example.order.gaode1;

import cn.hutool.json.JSONUtil;
import com.luoyi.example.order.utils.HttpUtils;

import java.util.HashMap;

/**
 * 查询商圈
 */
public class MainTest02 {

    public static void main(String[] args) {
        String url = "https://restapi.amap.com/rest/me/polygon/dataquery/sq";
        Test02 test02 = new Test02();
        test02.setKey("a2f03ffe48daaf826ad4d79e5864b947");
        test02.setAdcode("510100");
        String res = HttpUtils.post(url, new HashMap<>(), JSONUtil.toJsonStr(test02));
        System.out.println(res);
    }
}
