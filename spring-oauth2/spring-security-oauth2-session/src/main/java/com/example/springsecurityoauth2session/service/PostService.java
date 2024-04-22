package com.example.springsecurityoauth2session.service;

import com.example.springsecurityoauth2session.entiy.Post;
import com.example.springsecurityoauth2session.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public Long create(Post post) {
        return postRepository.save(post).getId();
    }



}
