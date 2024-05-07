# SpringBoot WebSocket
- https://spring.io/guides/gs/messaging-stomp-websocket


## WebSocketConfig.java
```java
@Configuration
@EnableWebSocketMessageBroker // 메시지 처리 활성화 (메시지 브로커를 지원)
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // [1] websocket 연걸 엔드포인트 구성 
        registry.addEndpoint("/ws-connect-endpoint").withSockJS();
    }
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 메모리 기반 메시지 브로커 활성화
        // 브로커는 /topic 으로 시작하는 목적지로 인사 메시지를 클라이언트에게 다시 전송
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app"); // localhost:8080/app/hello [2]
    }

}

```


## MainController.java
```java
@Controller
public class MainController {

    // localhost:8080/hello 로 요청이 온다면 1초 후
    // localhost:8080/topic/greetings 의 모든 구독자에게 데이터 전송
    @MessageMapping("/hello") // [2]
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws InterruptedException {
        Thread.sleep(1000);
        return new Greeting("Hello from server, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}

```
1. 클라이언트는 웹소켓을 통해 서버에 연결하려면 /ws-connect-endpoint 엔드포인트로 연결
2. 클라이언트는 소켓 연결 후, localhost:8080/app/hello 에 메세지 전송
3. 서버는 /topic/greetings 토픽으로 메세지를 브로드 캐스트
4. 클라이언트는 /topic/greetings 토픽을 구독하여 서버로부터 메세지를 받음