package com.example.springbootjparelationship.post;


import com.example.springbootjparelationship.member.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/webtoon")
@RestController
public class WebtoonController {

    private final WebtoonService webtoonService;

    @GetMapping
    public List<Webtoon> getAllPosts() {
        return webtoonService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Webtoon getPostById(@PathVariable Long id) {
        return webtoonService.getPostById(id);
    }

    @PostMapping
    public Webtoon createPost(@RequestBody Webtoon webtoon, @RequestBody Member member) {
        return webtoonService.createPost(webtoon,member);
    }

}
