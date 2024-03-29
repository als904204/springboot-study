package com.example.springcomparecaffeineehcache.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ehcache.xml 보다 우선순위
@Configuration
public class CaffeineCacheConfig {


    @Bean
    public List<CaffeineCache> caffeineConfig() {
        return Arrays.stream(CacheType.values())
            .map(cache -> new CaffeineCache(cache.getCacheName(), Caffeine.newBuilder()
                    .recordStats()
                    .expireAfterWrite(cache.getExpireAfterWrite(),
                        TimeUnit.SECONDS)
                    .maximumSize(cache.getMaximumSize())
                    .build()
                )
            )
            .collect(Collectors.toList());
    }

    @Bean
    public CacheManager cacheManager(List<CaffeineCache> caffeineCaches) {
        final SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(caffeineCaches);
        return cacheManager;
    }
}
