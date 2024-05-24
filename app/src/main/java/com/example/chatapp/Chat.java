package com.example.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Chat extends AppCompatActivity {

    private Retrofit retrofit;
    private ApiService apiService;
    private Button sendButton;
    private EditText messageEditText;
    private ListView messageListView;
    private List<ChatHistoryItem> chatHistory;
    private ChatAdapter chatAdapter;
    private String userInitial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        sendButton = findViewById(R.id.sendButton);
        messageEditText = findViewById(R.id.messageEditText);
        messageListView = findViewById(R.id.messageListView);

        Intent intent = getIntent();
        String username = intent.getStringExtra("USERNAME");
        userInitial = username != null && !username.isEmpty() ? username.substring(0, 1).toUpperCase() : "U";

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.20.22:5000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        chatHistory = new ArrayList<>();

        chatAdapter = new ChatAdapter(this, chatHistory, userInitial);
        messageListView.setAdapter(chatAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = messageEditText.getText().toString().trim();
                handleMessage(msg);
            }
        });
    }

    private void handleMessage(String userMessage) {
        sendClientReply(userMessage, chatHistory);
    }
    private void handleStaticReply(String userMessage, String staticReply) {
        ChatHistoryItem userItem = new ChatHistoryItem();
        userItem.setUser(userMessage);
        userItem.setLlama("");

        ChatHistoryItem replyItem = new ChatHistoryItem();
        replyItem.setUser("");
        replyItem.setLlama(staticReply);

        chatHistory.add(userItem);
        chatHistory.add(replyItem);

        chatAdapter.notifyDataSetChanged();
        messageEditText.setText("");
    }

    private void sendClientReply(String userMessage, List<ChatHistoryItem> chatHistory) {
        ChatRequest chatRequest = new ChatRequest();
        chatRequest.setUserMessage(userMessage);
        chatRequest.setChatHistory(chatHistory);

        Call<ChatResponse> call = apiService.sendClientReply(chatRequest);

        call.enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                if (response.isSuccessful()) {
                    ChatResponse responseBody = response.body();
                    if (responseBody != null) {
                        List<ChatHistoryItem> updatedHistory = responseBody.getChatHistory();
                        if (updatedHistory != null && !updatedHistory.isEmpty()) {
                            chatHistory.clear();
                            chatHistory.addAll(updatedHistory);
                            chatAdapter.notifyDataSetChanged();
                            Log.d("API", "Chat history updated");
                        }
                    }
                } else {
                    Log.d("API", "Request failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
                Log.d("API", "Request failed: " + t.getMessage());
            }
        });
    }
}
