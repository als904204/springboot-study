package com.example.springcomparecaffeineehcache.webtoon;


import java.util.List;
import javax.websocket.server.PathParam;
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

    @GetMapping
    public ResponseEntity<List<WebtoonDto>> findAll(@RequestParam String platform) {

        log.info("컨트롤러 호출");
        return ResponseEntity.ok().body(webtoonService.findAll(platform));
    }
}
