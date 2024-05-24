package com.example.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ChatAdapter extends BaseAdapter {

    private Context context;
    private List<ChatHistoryItem> chatHistory;
    private String userInitial;

    public ChatAdapter(Context context, List<ChatHistoryItem> chatHistory, String userInitial) {
        this.context = context;
        this.chatHistory = chatHistory;
        this.userInitial = userInitial;
    }

    @Override
    public int getCount() {
        return chatHistory.size();
    }

    @Override
    public Object getItem(int position) {
        return chatHistory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.chat_item, parent, false);
        }

        ChatHistoryItem chatHistoryItem = chatHistory.get(position);

        TextView userInitialTextView = convertView.findViewById(R.id.userInitialTextView);
        TextView userMessageTextView = convertView.findViewById(R.id.userMessageTextView);
        TextView llamaInitialTextView = convertView.findViewById(R.id.llamaInitialTextView);
        TextView llamaReplyTextView = convertView.findViewById(R.id.llamaReplyTextView);

        if (!chatHistoryItem.getUser().isEmpty()) {
            userInitialTextView.setVisibility(View.VISIBLE);
            userMessageTextView.setVisibility(View.VISIBLE);
            userInitialTextView.setText(userInitial);
            userMessageTextView.setText(chatHistoryItem.getUser());
        } else {
            userInitialTextView.setVisibility(View.GONE);
            userMessageTextView.setVisibility(View.GONE);
        }

        if (!chatHistoryItem.getLlama().isEmpty()) {
            llamaInitialTextView.setVisibility(View.VISIBLE);
            llamaReplyTextView.setVisibility(View.VISIBLE);
            llamaReplyTextView.setText(chatHistoryItem.getLlama());
        } else {
            llamaInitialTextView.setVisibility(View.GONE);
            llamaReplyTextView.setVisibility(View.GONE);
        }

        return convertView;
    }
}
