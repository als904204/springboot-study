package org.example.springwebsocket.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Override
    public String toString() {
        return "Topic{" +
            "id=" + id +
            ", title='" + title + '\'' +
            '}';
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Topic(String title) {
        this.title = title;
    }

    public Topic() {
    }
}
