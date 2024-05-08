package org.example.springwebsocket.controller;


import org.example.springwebsocket.domain.VoteRequest;
import org.example.springwebsocket.service.VoteService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocketController {


    private final VoteService voteService;

    public SocketController(VoteService voteService) {
        this.voteService = voteService;
    }

    /**
     * @SendTo 어노테이션을 사용하는 방식 해당 topics를 수신하는 Client Websocket에게 메시지 전달 리턴 타입을 정의해야하며 반환값 리턴을 통해
     * Client에게 전달
     */

    @MessageMapping("/vote")
    public void SendToMessage(@RequestBody VoteRequest voteRequest) {
        voteService.updateVoteResult(voteRequest.getTopicId());
    }


}
