package com.example.springcomparecaffeineehcache.webtoon;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class WebtoonService {

    private final WebtoonRepository webtoonRepository;

    @Cacheable(value = "platform", key = "#platform", condition = "#platform.equals('NAVER')")
    public List<WebtoonDto> findAll(String platform) {
        log.info("서비스 호출");

        List<Webtoon> webtoons = webtoonRepository.findByPlatform(platform);

        List<WebtoonDto> response = new ArrayList<>();


        for (Webtoon webtoon : webtoons) {
            response.add(new WebtoonDto(webtoon));
        }

        return response;
    }




    @PostConstruct
    void initTempData() {
        List<Webtoon> naver = new ArrayList<>();
        List<Webtoon> kakao = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            naver.add(new Webtoon("webtoon ", "NAVER"));
            kakao.add(new Webtoon("webtoon ", "KAKAO"));
        }


        webtoonRepository.saveAll(naver);
        webtoonRepository.saveAll(kakao);
    }

}
