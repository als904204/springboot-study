package org.example.springwebsocket.service;

import java.util.Optional;
import org.example.springwebsocket.domain.Topic;
import org.example.springwebsocket.domain.Vote;
import org.example.springwebsocket.repository.TopicRepository;
import org.example.springwebsocket.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InitData {

    private static final Logger log = LoggerFactory.getLogger(InitData.class);

    private final TopicRepository topicRepository;
    private final VoteRepository voteRepository;

    public InitData(TopicRepository topicRepository, VoteRepository voteRepository) {
        this.topicRepository = topicRepository;
        this.voteRepository = voteRepository;
    }

    public Long newTopic() {
        long cnt = topicRepository.count();
        long tempTitle = cnt + 1L;
        Topic topic = new Topic("temp_topic_" + tempTitle);
        topic = topicRepository.save(topic);

        log.info("topic saved={}, count={}", topic, cnt);
        return topic.getId();
    }

    public Long newVote() {
        Vote vote;
        Optional<Topic> topicOptional = topicRepository.findById(1L);

        Topic topic;
        if (topicOptional.isPresent()) {
            topic = topicOptional.get();
        }else {
            topic = new Topic("temp_topic_1");
            topicRepository.save(topic);
        }

        vote = new Vote(topic.getId());
        vote = voteRepository.save(vote);
        log.info("vote saved={}, count={}", vote, voteRepository.count());

        return vote.getId();
    }

}
