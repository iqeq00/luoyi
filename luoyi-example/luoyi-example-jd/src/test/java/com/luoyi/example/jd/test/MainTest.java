package com.luoyi.example.jd.test;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainTest {

    public static void main(String[] args) {
//        System.out.println("111");
//        List<String> elements = List.of("Element1", "Element2", "Element3", "Element4");
//        Timer timer = new Timer(true); // 创建一个守护线程的Timer

//        // 定义打印任务
//        TimerTask printTask = new TimerTask() {
//            private int index = 0;
//
//            @Override
//            public void run() {
//                if (index < elements.size()) {
////                    System.out.println(elements.get(index));
////                    System.out.println("打印元素: " + elements.get(index) + "，时间：" + LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"));
//                    index++;
//                } else {
//                    timer.cancel(); // 所有元素打印完毕后取消Timer
//                }
//            }
//        };
//
//        // 每2分钟执行一次任务
//        timer.scheduleAtFixedRate(printTask, 0, 5 * 1000);

        List<String> elements = List.of("Element1", "Element2", "Element3", "Element4");

        Object[] args1 = new Object[5];
        args1[0] = LocalDateTime.now();
        args1[1] = "1111";
        args1[2] = 222;
        args1[3] = elements;

        System.out.println(StrUtil.join(",", args1));
    }
}
