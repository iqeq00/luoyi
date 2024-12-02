package com.luoyi.example.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.luoyi.example.mybatis.mapper")
public class LuoyiExampleMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuoyiExampleMybatisApplication.class, args);
	}

}
