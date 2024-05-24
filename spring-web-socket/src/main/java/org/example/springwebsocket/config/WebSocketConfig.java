package org.example.springwebsocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // 메시지 처리 활성화 (메시지 브로커를 지원)
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
            .setAllowedOriginPatterns("*")
            .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //topic : 1:N, 다수에게 메시지 보낼 때 사용, /topic/주소
        //queue : 1:1, 특정 대상에게 메시지 보낼 때 사용, /queue/주소
        registry.enableSimpleBroker("/topic");

        //메시지를 보내는 prefix로 app을 사용
        //클라이언트 -> 서버 : client.send(`/app/chat/보낼주소`,{},JSON.stringify(보낼데이터))
        //registry.setApplicationDestinationPrefixes("/app");
    }

}
