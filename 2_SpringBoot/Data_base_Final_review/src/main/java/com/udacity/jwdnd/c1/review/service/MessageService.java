package com.udacity.jwdnd.c1.review.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.c1.review.model.ChatForm;
import com.udacity.jwdnd.c1.review.model.ChatMessage;

@Service
public class MessageService {
    private List<ChatMessage> chatMessages;

//    no longer needed
//
//    private String message;
//
//    public MessageService(String message) {
//        this.message = message;
//    }
//
//    public String uppercase() {
//        return this.message.toUpperCase();
//    }
//
//    public String lowercase() {
//        return this.message.toLowerCase();
//    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating MessageService bean");
        this.chatMessages = new ArrayList<>();
    }

    public void addMessage(ChatForm chatForm) {
        ChatMessage newMessage = new ChatMessage();
        newMessage.setUsername(chatForm.getUsername());
        switch (chatForm.getMessageType()) {
            case "Say":
                newMessage.setMessage(chatForm.getMessageText());
                break;
            case "Shout":
                newMessage.setMessage(chatForm.getMessageText().toUpperCase());
                break;
            case "Whisper":
                newMessage.setMessage(chatForm.getMessageText().toLowerCase());
                break;
            case "Talk":
                newMessage.setMessage(chatForm.getMessageText().concat(", He Said"));
                break;
            default:
                newMessage.setMessage("INVALID");
                break;
        }
        this.chatMessages.add(newMessage);
        MessageMapper.insert(new Message(null, newMessage.getUsername(), newMessage.getMessage()))
    }

    public List<ChatMessage> getChatMessages() {
        return MessageMapper.getAllMessage();
    }
}
