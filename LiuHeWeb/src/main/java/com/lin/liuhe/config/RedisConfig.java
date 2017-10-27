package com.lin.liuhe.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

@Configuration
@PropertySource(value="classpath:liu-redis.properties")
public class RedisConfig {
	@Value("${redis.host}")
	private String redisHost1;
	@Value("${redis.port}")
	private String redisPort1;
	@Bean
	public ShardedJedisPool getShardedJedisPool() {
		//redis主机
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		JedisShardInfo dedisShardInfo1 = new JedisShardInfo(redisHost1,redisPort1);
		shards.add(dedisShardInfo1);
		//<!-- 连接池信息 -->
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(50);
		//<!-- 分片式集群 -->
		ShardedJedisPool shardedJedisPool = new ShardedJedisPool(jedisPoolConfig, shards );
		return shardedJedisPool;
	}
}
