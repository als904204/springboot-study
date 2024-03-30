package com.example.pageandslice.post;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> findAllByOffset(@RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        List<Post> response = postService.findAllByOffset(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/v1")
    public ResponseEntity<List<Post>> findAllByNoOffset(
        @RequestParam(value = "postId", required = false) Long postId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        List<Post> response = postService.findAllByNoOffset(postId, page, size);
        return ResponseEntity.ok(response);
    }
}
