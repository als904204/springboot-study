package com.example.springbootjparelationship.member;


import com.example.springbootjparelationship.comment.Review;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Member member) {
        memberService.signup(member);
        return new ResponseEntity<>("Signed up successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Member> login(@RequestBody Member member) {
        Member login = memberService.login(member);
        return ResponseEntity.ok(login);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewDto>>reviews(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getReviewsByMember(id));
    }

    @Getter
    @AllArgsConstructor
    static class ReviewDto {

        private Long id;
        private String content;

        public ReviewDto(Review review) {
            id = review.getId();
            content = review.getContent();
        }

    }


}
