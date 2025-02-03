package com.compare.redis_vs_caffeine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisVsCaffeineApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisVsCaffeineApplication.class, args);
	}

}
