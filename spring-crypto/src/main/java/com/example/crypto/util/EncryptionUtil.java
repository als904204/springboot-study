package com.example.crypto.util;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private final SecretKey aesSecretKey;
    private final IvParameterSpec iv;

    public EncryptionUtil(@Value("${secret.key}") String secretKey) {
        this.aesSecretKey = new SecretKeySpec(secretKey.getBytes(), "AES");
        this.iv = new IvParameterSpec(new byte[16]);
    }

    public String encrypt(String input) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, aesSecretKey, iv);
            return Base64.getEncoder().encodeToString(cipher.doFinal(input.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException("Encryption error", e);
        }
    }

    public String decrypt(String encrypted) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, aesSecretKey, iv);
            return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));
        } catch (Exception e) {
            throw new RuntimeException("Decryption error", e);
        }
    }

}
