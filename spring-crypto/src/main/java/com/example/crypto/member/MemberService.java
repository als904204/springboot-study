package com.example.crypto.member;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional
    public Long createMember(Member req) {

        Member newMember = Member.builder()
            .phone(req.getPhone())
            .email(req.getEmail())
            .build();

        return memberRepository.save(newMember).getId();
    }

}
