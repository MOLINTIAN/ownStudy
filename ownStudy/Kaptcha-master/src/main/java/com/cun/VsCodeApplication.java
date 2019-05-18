package com.cun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class VsCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(VsCodeApplication.class, args);
	}
}
