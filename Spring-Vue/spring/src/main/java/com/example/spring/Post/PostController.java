package com.example.spring.Post;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/posts")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;


    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.findPostById(id));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.findAllPosts());
    }

    @PostMapping
    public ResponseEntity<Long> savePost(SaveDto dto) {
        return ResponseEntity.ok(postService.save(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable("id") Long id, UpdateDto dto) {
        postService.updateByID(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id) {
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
