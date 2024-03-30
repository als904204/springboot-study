package com.example.springbootjparelationship.post;


import com.example.springbootjparelationship.member.Member;
import com.example.springbootjparelationship.member.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class WebtoonService {

    private final WebtoonRepository webtoonRepository;
    private final MemberRepository memberRepository;


    public List<Webtoon> getAllPosts() {
        return webtoonRepository.findAll();
    }

    public Webtoon getPostById(Long id) {
        return webtoonRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    public Webtoon createPost(Webtoon webtoon, Member member) {
        memberRepository.findById(member.getId()).orElseThrow(() -> new RuntimeException("회원 없음"));
        webtoon.setAuthor(member);
        return webtoonRepository.save(webtoon);
    }

}
