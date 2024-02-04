package com.example.crypto.converter;


import com.example.crypto.util.EncryptionUtil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class CryptoConverter implements AttributeConverter<String, String> {


    private final EncryptionUtil encryptionUtil;

    /*
     * 데이터베이스에 요청을 보낼 때 (암호화)
     * String phone -> varchar phone
     * String email -> varchar email
     * */
    @Override
    public String convertToDatabaseColumn(String attribute) {
        return encryptionUtil.encrypt(attribute);
    }

    /*
     * 데이터베이스에서 값을 읽어올 때 (복호화)
     * varchar phone -> String phone
     * varchar email -> String email
     * */
    @Override
    public String convertToEntityAttribute(String dbData) {
        return encryptionUtil.decrypt(dbData);
    }
}
