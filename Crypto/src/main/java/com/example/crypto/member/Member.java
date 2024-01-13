package com.example.crypto.member;

import com.example.crypto.converter.CryptoConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * 1. 데이터 베이스에 저장될 때 암호화
     * 2. 데이터 베이스에서 값을 읽어올 때 복호화
     * */
    @Convert(converter = CryptoConverter.class)
    private String phone;

    @Convert(converter = CryptoConverter.class)
    private String email;

    @Builder
    public Member(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }
}
