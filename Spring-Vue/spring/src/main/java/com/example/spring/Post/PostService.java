package com.example.spring.Post;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }
    public Post findPostById(Long id) {
        return postRepository.findById(id).orElseThrow();
    }
    public Long save(SaveDto dto) {
        Post newPost = Post.builder()
            .content(dto.getContent())
            .title(dto.getTitle())
            .build();
        return postRepository.save(newPost).getId();
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    public void updateByID(Long id, UpdateDto dto) {
        Post post = postRepository.findById(id).orElseThrow();
        post.update(dto.getTitle(), dto.getContent());
    }


    @PostConstruct
    public void initData() {
        List<Post> posts = new ArrayList<>();
        if (postRepository.count() == 0) {
            log.info("init temp post data");
            for (int i = 1; i <= 30; i++) {
                Post post = Post.builder()
                    .content("temp content" + i)
                    .title("temp title" + i)
                    .build();
                posts.add(post);
            }
        }
        postRepository.saveAll(posts);
    }

}
