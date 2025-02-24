package com.luoyi.example.jd.test;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainTest1 {

    public static void main(String[] args) {
//        List<String> elements = new ArrayList<>();
//        elements.add("Element1");
//        elements.add("Element2");
//        elements.add("Element3");
//        elements.add("Element4");

        List<String> elements = List.of("Element1", "Element2", "Element3", "Element4");

        CronUtil.schedule("*/125 * * * * *", new Task() {
            int index = 0;
            @Override
            public void execute() {
                if (index < elements.size()) {
                    System.out.println("打印元素: " + elements.get(index) + "，时间：" + LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"));
                    index++;
                } else {
                    System.out.println("所有元素打印完成，任务退出");
                    CronUtil.stop(); // 停止任务
                }
            }
        });

        // 支持秒级别定时任务
        CronUtil.setMatchSecond(true);
        CronUtil.start();
    }
}
