package com.compare.redis_vs_caffeine.service;

import com.compare.redis_vs_caffeine.entity.Posts;
import com.compare.redis_vs_caffeine.repository.PostsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    /**
     * 조회 후 cache 저장
     */
    @Cacheable(value = "posts", key = "'postsList'")
    public List<Posts> getPostsData() {
        return postsRepository.findAll();
    }
}
