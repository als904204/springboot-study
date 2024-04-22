package com.example.springsecurityoauth2session.controller;

import com.example.springsecurityoauth2session.entiy.Post;
import com.example.springsecurityoauth2session.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/posts")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Long> create(Post post) {
        Long id = postService.create(post);
        return ResponseEntity.ok(id);
    }

}
