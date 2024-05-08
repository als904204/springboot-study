package org.example.springwebsocket.controller;

import org.example.springwebsocket.service.InitData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/topic")
@RestController
public class TopicController {

    private final InitData initData;

    public TopicController(InitData initData) {
        this.initData = initData;
    }

    @PostMapping("/init")
    public ResponseEntity<Long> initTopic() {
        return ResponseEntity.ok(initData.newTopic());
    }

}
