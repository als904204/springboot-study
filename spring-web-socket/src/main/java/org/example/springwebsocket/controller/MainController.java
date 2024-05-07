package org.example.springwebsocket.controller;

import org.example.springwebsocket.domain.Greeting;
import org.example.springwebsocket.domain.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MainController {

    // localhost:8080/hello 로 요청이 온다면 1초 후
    // localhost:8080/topic/greetings 의 모든 구독자에게 데이터 전송
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws InterruptedException {
        Thread.sleep(1000);
        return new Greeting("Hello from server, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}
