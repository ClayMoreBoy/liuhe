package com.lin.liuhe.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.jolbox.bonecp.BoneCPDataSource;
//@Configuration
public class DbConfig {
	@Value("${lin.user}")
	private String dbUserName;
	@Value("${lin.driverClass}")
	private String dbDriver;
	@Value("${lin.jdbcUrl}")
	private String dbUrl;
	@Value("${lin.password}")
	private String dbPassword;

	@Bean(destroyMethod="close")
	public DataSource getDataSource() {
		BoneCPDataSource dataSource = new BoneCPDataSource();
		dataSource.setUser(dbUserName);
		dataSource.setPassword(dbPassword);
		dataSource.setDriverClass(dbDriver);
		dataSource.setJdbcUrl(dbUrl);
		return dataSource;
	}
}
