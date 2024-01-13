package com.study.cache.book;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final CacheBookRepository cacheBookRepository;
    private final SimpleBookRepository simpleBookRepository;


    public AppRunner(CacheBookRepository cacheBookRepository,
        SimpleBookRepository simpleBookRepository) {
        this.cacheBookRepository = cacheBookRepository;
        this.simpleBookRepository = simpleBookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long startTimeNoCache = System.nanoTime();
        logger.info("캐시 안쓴 repository");
        logger.info("책 찾는중...");
        logger.info("isbn-1234 -->" + simpleBookRepository.getByIsbn("isbn-1234"));
        logger.info("isbn-4567 -->" + simpleBookRepository.getByIsbn("isbn-4567"));
        logger.info("isbn-1234 -->" + simpleBookRepository.getByIsbn("isbn-1234"));
        logger.info("isbn-4567 -->" + simpleBookRepository.getByIsbn("isbn-4567"));
        logger.info("isbn-1234 -->" + simpleBookRepository.getByIsbn("isbn-1234"));
        logger.info("isbn-1234 -->" + simpleBookRepository.getByIsbn("isbn-1234"));
        long endTimeNoCache = System.nanoTime();
        logger.info("걸린 시간(캐시 미사용): " + (endTimeNoCache - startTimeNoCache));

        long startTimeCache = System.nanoTime();
        logger.info("캐시 사용 repository");
        logger.info("책 찾는중...");
        logger.info("isbn-1234 -->" + cacheBookRepository.getByIsbn("isbn-1234"));
        logger.info("isbn-4567 -->" + cacheBookRepository.getByIsbn("isbn-4567"));
        logger.info("isbn-1234 -->" + cacheBookRepository.getByIsbn("isbn-1234"));
        logger.info("isbn-4567 -->" + cacheBookRepository.getByIsbn("isbn-4567"));
        logger.info("isbn-1234 -->" + cacheBookRepository.getByIsbn("isbn-1234"));
        logger.info("isbn-1234 -->" + cacheBookRepository.getByIsbn("isbn-1234"));
        long endTimeCache = System.nanoTime();
        logger.info("걸린 시간(캐시 사용): " + (endTimeCache - startTimeCache));

    }
}
