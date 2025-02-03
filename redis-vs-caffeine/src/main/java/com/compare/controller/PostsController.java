package com.compare.controller;

import com.compare.redis_vs_caffeine.entity.Posts;
import com.compare.redis_vs_caffeine.service.PostsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsController {

    private final PostsService postsService;

    @GetMapping("/posts")
    public List<Posts> getPosts() {
        return postsService.getPostsData();
    }

}
