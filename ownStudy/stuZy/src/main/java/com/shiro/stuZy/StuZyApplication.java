package com.shiro.stuZy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan(basePackages = "com.shiro.stuZy.dao")
public class StuZyApplication {

	public static void main(String[] args) {
		SpringApplication.run(StuZyApplication.class, args);
	}

}
