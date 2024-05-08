package org.example.springwebsocket.repository;

import java.util.List;
import org.example.springwebsocket.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findByTopicId(Long topicId);

}
