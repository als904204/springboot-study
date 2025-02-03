package com.compare.redis_vs_caffeine.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String content;

    private int voteCount;

    public Posts() {}

    public Posts(String title, String content, int voteCount) {
        this.title = title;
        this.content = content;
        this.voteCount = voteCount;
    }

}

