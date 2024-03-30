package com.example.springbootjparelationship.comment;


import com.example.springbootjparelationship.member.Member;
import com.example.springbootjparelationship.member.MemberRepository;
import com.example.springbootjparelationship.post.Webtoon;
import com.example.springbootjparelationship.post.WebtoonRepository;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final WebtoonRepository webtoonRepository;

    @PostConstruct
    void init() {

        Member member = new Member(1L, "user", "email");

        memberRepository.save(member);
        log.info("회원 init={}", member);


        Webtoon webtoon = new Webtoon();
        webtoon.setAuthor(member);
        webtoon.setContent("content");
        webtoon.setTitle("title");

        webtoonRepository.save(webtoon);
        log.info("웹툰 init={}", webtoon);

        List<Review> reviews = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Review review = new Review();
            review.setContent("content");
            review.setWebtoon(webtoon);
            review.setMember(member);
            reviews.add(review);
        }


        reviewRepository.saveAll(reviews);
        log.info("리뷰 init={}", reviews.size());

    }
}
