package org.example.springwebsocket.domain;

public class TopicRequest {

    private String title;

    public TopicRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
