package com.lin.liuhe.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(value= {"com.lin.liuhe"})
@PropertySource(value= {"classpath:db.properties","classpath:liu-connection.properties"})
public class LiuHeApplication {
	public static void main(String[] args) {
		SpringApplication.run(LiuHeApplication.class, args);
	}
}
