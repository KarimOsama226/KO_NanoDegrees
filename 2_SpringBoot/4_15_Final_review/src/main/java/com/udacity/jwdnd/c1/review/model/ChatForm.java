package com.udacity.jwdnd.c1.review.model;

public class ChatForm {
    private String username;
    private String messageText;
    private String msgTyp;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageType() {
        return msgTyp;
    }

    public void setMessageType(String messageType) {
        this.msgTyp = messageType;
    }
}
