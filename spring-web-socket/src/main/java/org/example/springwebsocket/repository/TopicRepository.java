package org.example.springwebsocket.repository;

import org.example.springwebsocket.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic,Long> {

}
