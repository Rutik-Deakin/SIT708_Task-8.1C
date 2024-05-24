package com.example.chatapp;

import java.util.List;

public class ChatResponse {
    private String userMessage;
    private List<ChatHistoryItem> chatHistory;

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public List<ChatHistoryItem> getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(List<ChatHistoryItem> chatHistory) {
        this.chatHistory = chatHistory;
    }
}

class ChatHistoryItem {
    private String user;
    private String llama;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLlama() {
        return llama;
    }

    public void setLlama(String llama) {
        this.llama = llama;
    }
}
