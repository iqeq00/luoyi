package com.luoyi.example.jd.test.copy;

import cn.hutool.core.bean.BeanUtil;

public class MainTest2 {

    public static void main(String[] args) {

        BaseResult<Personal> baseResult = new BaseResult<>();
        Personal personal = new Personal();
        baseResult.setData(personal);

        Personal1 personal1 = new Personal1();
        personal1.setAge(18);
        personal1.setName("算上");

        BeanUtil.copyProperties(personal1, baseResult.getData());

        System.out.println(baseResult.getData().getAge());
    }
}
