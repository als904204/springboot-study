package com.example.springcomparecaffeineehcache.ehcache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/ehcache")
@RequiredArgsConstructor
@RestController
public class EhcacheController {

    private final EhcacheService ehcacheService;

    @GetMapping("/{id}")
    public int total(@PathVariable int id) {
        log.info("[Ehcache] controller 호출");
        return ehcacheService.status(id);
    }

}
