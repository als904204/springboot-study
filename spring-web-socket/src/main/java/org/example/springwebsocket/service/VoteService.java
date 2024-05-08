package org.example.springwebsocket.service;

import java.util.List;
import org.example.springwebsocket.domain.Vote;
import org.example.springwebsocket.domain.VoteRequest;
import org.example.springwebsocket.repository.VoteRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public VoteService(VoteRepository voteRepository, SimpMessagingTemplate messagingTemplate) {
        this.voteRepository = voteRepository;
        this.messagingTemplate = messagingTemplate;
    }

    public void updateVoteResult(Long topicId) {
        List<Vote> votes = voteRepository.findByTopicId(topicId);
        messagingTemplate.convertAndSend("/topic/votes", votes);
    }

    public Long saveVote(VoteRequest voteRequest) {
        Vote vote = new Vote(voteRequest.getTopicId());
        return voteRepository.save(vote).getId();
    }


}
