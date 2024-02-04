package com.example.springsecurityoauth2session.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
public class CustomClientRegistrationRepository {

    private final SocialClientRegistration clientRegistration;

    public CustomClientRegistrationRepository(SocialClientRegistration clientRegistration) {
        this.clientRegistration = clientRegistration;
    }

    // 인 메모리에 저장하는 이유
    // OAuth2 가입이 아무리 많아도 4개 정도여서 인 메모리에 저장해도 저장공간을 많이 차지하지 않음
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(
            clientRegistration.naverClientRegistration());
    }

    /**
     * 여러개의 OAuth 등록
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(
            clientRegistration.naverClientRegistration(),clientRegistration.googleClientRegistration);
    }
     */

}
