package com.example.springcomparecaffeineehcache.ehcache;


import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class EhcacheService {

    @Cacheable(value = "foo", key = "#id")
    public int status(int id) {
        log.info("Service 호출");
        Random random = new Random();
        return random.nextInt();
    }

}
