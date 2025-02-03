package com.compare.redis_vs_caffeine.service;

import com.compare.redis_vs_caffeine.dto.PageResponse;
import com.compare.redis_vs_caffeine.entity.Posts;
import com.compare.redis_vs_caffeine.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    /**
     * 페이지별 조회 후 캐시 저장 (페이지 1~10만 캐시 적용)
     */
    @Cacheable(value = "posts", key = "'postsPage_' + #page", condition = "#page >= 1 and #page <= 10")
    public PageResponse getPostsData(int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("id").ascending());
        Page<Posts> postsPage = postsRepository.findAll(pageable);
        return new PageResponse(
            postsPage.getContent(),
            postsPage.getNumber() + 1,
            postsPage.getSize(),
            postsPage.getTotalElements()
        );
    }
}
