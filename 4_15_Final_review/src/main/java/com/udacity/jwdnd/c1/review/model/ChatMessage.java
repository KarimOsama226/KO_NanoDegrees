package com.udacity.jwdnd.c1.review.model;

public class ChatMessage {
    private String username;
    private String msgTxt;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return msgTxt;
    }

    public void setMessage(String message) {
        this.msgTxt = message;
    }
}
