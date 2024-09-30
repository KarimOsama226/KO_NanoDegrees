package com.udacity.jwdnd.c1.review.model;

public class ChatMessage {
    private String username;
    private String msgTxt;
    private int messageId;

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
    public int getMessageId() {
        return this.messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
}
