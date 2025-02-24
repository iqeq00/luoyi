package com.luoyi.example.jd.test;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;

import java.util.List;

public class MainTest2 {

    public static void main(String[] args) {
        // 示例 List
        List<String> elements = List.of("Element1", "Element2", "Element3", "Element4");

        // 使用 Hutool 的 TimerTask
        ThreadUtil.execAsync(() -> {
            for (String element : elements) {
                System.out.println(element); // 打印元素
                ThreadUtil.sleep(5000); // 每5秒打印一次
            }
            System.out.println("所有元素打印完成，任务退出");
        });
    }
}
