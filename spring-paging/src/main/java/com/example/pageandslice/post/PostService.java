package com.example.pageandslice.post;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final JPAQueryFactory queryFactory;



    @Transactional(readOnly = true)
    public List<Post> findAllByOffset(int page, int size) {
        QPost post = QPost.post;

        return queryFactory.select(post)
            .from(post)
            .orderBy(post.id.desc())
            .limit(size)
            .offset(page * size)
            .fetch();
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByNoOffset(Long postId, int page, int size) {
        QPost post = QPost.post;

        return queryFactory
            .select(post)
            .from(post)
            .where(ltPostId(postId))
            .orderBy(post.id.desc())
            .limit(size)
            .fetch();
    }

    private BooleanExpression ltPostId(Long postId) {
        QPost post = QPost.post;
        if (postId == null) {
            return null;
        }

        return post.id.lt(postId);
    }


    @PostConstruct
    void initData() {
        int size = 10000;
        List<Post> posts = new ArrayList<>(size);


        for (int i = 0; i < size; i++) {
            Post post = new Post("title","content");
            posts.add(post);
        }

        postRepository.saveAll(posts);
    }
}

