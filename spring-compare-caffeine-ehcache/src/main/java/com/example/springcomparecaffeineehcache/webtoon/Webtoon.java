package com.example.springcomparecaffeineehcache.webtoon;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Webtoon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public String platform;

    public Webtoon(String name,String paltform) {
        this.name = name;
        this.platform = paltform;
    }


    protected Webtoon() {

    }
}
