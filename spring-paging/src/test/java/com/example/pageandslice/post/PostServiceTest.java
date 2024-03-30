package com.example.pageandslice.post;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
class PostServiceTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @BeforeEach
    void initData() {
        int size = 100;
        List<Post> posts = new ArrayList<>(size);


        for (int i = 0; i < size; i++) {
            Post post = new Post("title","content");
            posts.add(post);
        }

        postRepository.saveAll(posts);
    }


    @Test
    void findAllByPaging() {

    }


}