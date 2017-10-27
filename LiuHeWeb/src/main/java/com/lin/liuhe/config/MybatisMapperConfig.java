package com.lin.liuhe.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(MybatisConfig.class)//保证在MyBatisConfig实例化之后再实例化该类
public class MybatisMapperConfig {
	private static final String BASEPACKAGEMAPPER="com.lin.liuhe.mapper"; 
	
	public MapperScannerConfigurer getMapperScannerConfigurer() {
		MapperScannerConfigurer scanner = new MapperScannerConfigurer();
		scanner.setBasePackage(BASEPACKAGEMAPPER);
		return scanner;
	}
}
