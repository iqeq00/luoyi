package com.luoyi.example.yihu.jwt;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.luoyi.example.yihu.vo.YihuBaseReq;
//import org.springframework.util.ObjectUtils;

import java.util.Objects;

public class MainTest {

    public static void main(String[] args) {
        YihuBaseReq yihuBaseReq = new YihuBaseReq();
        System.out.println(Objects.isNull(null));
        System.out.println(Objects.isNull(yihuBaseReq));
//        System.out.println(ObjectUtils.isEmpty(yihuBaseReq));
        System.out.println(ObjectUtils.isEmpty(yihuBaseReq));
        System.out.println(ObjectUtils.isNull(yihuBaseReq));
        System.out.println(BeanUtil.isEmpty(yihuBaseReq));
        System.out.println("========");
        yihuBaseReq.setSignType("111");
        System.out.println(BeanUtil.isEmpty(yihuBaseReq));
        System.out.println(BeanUtil.isEmpty(null));



//        try {
//            System.out.println(yihuBaseReq.getAppCode().charAt(0));
//        } catch (Exception e) {
////            throw new RuntimeException(e);
//            System.out.println(e);
//            System.out.println(e.getMessage());
//        }
    }
}
