package com.luoyi.example.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {
//        "com.luoyi.example.order.v3.config",    // 状态机配置类所在包
//        "com.luoyi.example.order.v3.service",   // 服务类所在包
//        "com.luoyi.example.order.controller" // 控制器所在包
//})
public class LuoyiExampleOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuoyiExampleOrderApplication.class, args);
    }

}
