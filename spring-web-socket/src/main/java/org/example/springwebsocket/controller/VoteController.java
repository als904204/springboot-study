package org.example.springwebsocket.controller;

import org.example.springwebsocket.domain.VoteRequest;
import org.example.springwebsocket.service.InitData;
import org.example.springwebsocket.service.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/vote")
@RestController
public class VoteController {

    private final VoteService voteService;
    private final InitData initData;

    public VoteController(VoteService voteService, InitData initData) {
        this.voteService = voteService;
        this.initData = initData;
    }

    @GetMapping
    public void getVote(@RequestParam Long topicId) {
        voteService.updateVoteResult(topicId);
    }

    @PostMapping
    public ResponseEntity<Long> postVote(@RequestBody VoteRequest voteRequest) {
        return ResponseEntity.ok(voteService.saveVote(voteRequest));
    }

    @PostMapping("/init")
    public ResponseEntity<Long> initVote() {
        return ResponseEntity.ok(initData.newVote());
    }


}
