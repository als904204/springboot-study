package com.example.crypto.member;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;


    @DisplayName("암호화 저장/복호화")
    @Test
    void crypto() {
        final String phone = "010-1234-1234";
        final String email = "example@hello.com";

        Member member = Member.builder()
            .phone(phone)
            .email(email)
            .build();
        System.out.println("=====저장 START======");
        memberRepository.save(member);
        System.out.println("=====저장 END======");



        System.out.println("=====이메일 조회 START======");
        Member findByEmail = memberRepository.findByEmail(email);
        assertThat(member.getEmail()).isEqualTo(findByEmail.getEmail());
        System.out.println("=====이메일 조회 END======");

        System.out.println("=====폰번호 조회 START======");
        Member findByPhone = memberRepository.findByPhone(phone);
        assertThat(member.getPhone()).isEqualTo(findByPhone.getPhone());
        System.out.println("=====폰번호 조회 END======");

    }
}