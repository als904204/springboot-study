package com.compare.redis_vs_caffeine.repository;


import com.compare.redis_vs_caffeine.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
