package com.example.springcomparecaffeineehcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringCompareCaffeineEhcacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCompareCaffeineEhcacheApplication.class, args);
	}

}
