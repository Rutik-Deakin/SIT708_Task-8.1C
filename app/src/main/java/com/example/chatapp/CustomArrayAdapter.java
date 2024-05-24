package com.example.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<String> {
    private Context context;
    private ArrayList<String> messages;

    public CustomArrayAdapter(Context context, int resource, ArrayList<String> messages) {
        super(context, resource, messages);
        this.context = context;
        this.messages = messages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView;
        if (convertView == null) {
            rowView = inflater.inflate(R.layout.item_message_sender, parent, false);
        } else {
            rowView = convertView;
        }

        TextView messageTextView = rowView.findViewById(R.id.messageTextView);
        messageTextView.setText(messages.get(position));

        return rowView;
    }
}
