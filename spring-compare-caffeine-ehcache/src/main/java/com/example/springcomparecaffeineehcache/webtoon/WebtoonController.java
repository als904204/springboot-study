package com.example.springcomparecaffeineehcache.webtoon;


import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/webtoons")
@RequiredArgsConstructor
@RestController
public class WebtoonController {

    private final WebtoonService webtoonService;

    @GetMapping("/ehcache")
    public ResponseEntity<List<WebtoonDto>> webtoonsByEhcache(@RequestParam String platform) {
        log.info("[ehcache] 컨트롤러 호출");
        return ResponseEntity.ok().body(webtoonService.findPlatformWebtoonsByEhcache(platform));
    }

    @GetMapping("/caffeine")
    public ResponseEntity<List<WebtoonDto>> webtoonsByCaffeine(@RequestParam String platform) {
        log.info("[caffeine] 컨트롤러 호출");
        return ResponseEntity.ok().body(webtoonService.findPlatformWebtoonsByCaffeine(platform));
    }

}
