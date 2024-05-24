package com.example.chatapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/chat")
    Call<ChatResponse> sendClientReply(@Body ChatRequest chatRequest);
}