package com.example.springcomparecaffeineehcache.webtoon;

import java.io.Serializable;
import lombok.Getter;

@Getter
public class WebtoonDto implements Serializable {

    private int id;
    private String name;
    private String platform;
    public WebtoonDto() {

    }
    public WebtoonDto(Webtoon webtoon) {
        this.id = webtoon.getId();
        this.name = webtoon.getName();
        this.platform = webtoon.getPlatform();
    }
}
