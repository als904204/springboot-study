package com.example.springbootjparelationship.member;


import com.example.springbootjparelationship.comment.Review;
import com.example.springbootjparelationship.comment.ReviewRepository;
import com.example.springbootjparelationship.member.MemberController.ReviewDto;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;


    public void signup(Member member) {
        memberRepository.save(member);
    }

    public Member login(Member member) {
        return memberRepository.findById(member.getId()).orElseThrow(() -> new RuntimeException("회원 없음"));
    }


    public List<ReviewDto> getReviewsByMember(Long memberId) {
        List<Review> reviews = reviewRepository.findByMemberId(memberId);

        return reviews.stream()
            .map(ReviewDto::new)
            .collect(Collectors.toList());
    }


}
