package com.lin.liuhe.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class MybatisConfig {
	private final static String TYPEALIASESPACKAGE = "com.lin.liuhe.pojo";
	private final static String MYBATISLOCATIONSOURCES = "classpath:mybatis.xml";
	private final static String MYBATIMAPPERSLOCATIONSOURCES = "classpath:com/lin/liuhe/mapper/*.xml";
	@Autowired
	private DataSource dataSource;

	@Bean
	@ConditionalOnMissingBean//当容器里没有指定的Bean的情况下创建该对象
	public SqlSessionFactoryBean getSqlSessionFactory() {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		//加载数据源
		sessionFactory.setDataSource(dataSource);
		//javabean别名
		sessionFactory.setTypeAliasesPackage(TYPEALIASESPACKAGE);
		//加载mybatis配置资源
		Resource configLocation =new PathMatchingResourcePatternResolver().getResource(MYBATISLOCATIONSOURCES);
		sessionFactory.setConfigLocation(configLocation);
		//加载映射文件
		try {
			Resource[] mapperLocations= new PathMatchingResourcePatternResolver().getResources(MYBATIMAPPERSLOCATIONSOURCES);
			sessionFactory.setMapperLocations(mapperLocations);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sessionFactory;
	}
}
