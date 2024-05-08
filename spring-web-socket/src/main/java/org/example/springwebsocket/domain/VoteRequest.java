package org.example.springwebsocket.domain;

public class VoteRequest {

    private Long topicId;


    public VoteRequest(Long topicId) {
        this.topicId = topicId;
    }

    public Long getTopicId() {
        return topicId;
    }


}
