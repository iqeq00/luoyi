package com.luoyi.example.jd.test;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.thread.ThreadUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainTest3 {

    public static void main(String[] args) {

        List<String> elements = List.of("Element1", "Element2", "Element3", "Element4");
        Timer timer = new Timer(true);
        TimerTask task = new TimerTask() {
            private int index = 0;
            @Override
            public void run() {
                if (index < elements.size()) {
//                    System.out.println(elements.get(index));
                    System.out.println("打印元素: " + elements.get(index) + "，时间：" + LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"));
                    index++;
                } else {
                    timer.cancel(); // 所有元素打印完毕后取消Timer
                }
            }
        };

        // 设置定时任务，每隔10秒执行一次
        timer.schedule(task, 0, 5000); //
    }
}
