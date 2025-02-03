package com.compare.redis_vs_caffeine.controller;

import com.compare.redis_vs_caffeine.dto.PageResponse;
import com.compare.redis_vs_caffeine.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsController {

    private final PostsService postsService;

    // http://localhost:8080/posts?page=1
    @GetMapping("/posts")
    public PageResponse getPosts(@RequestParam(defaultValue = "1") int page) {
        return postsService.getPostsData(page);
    }

}
