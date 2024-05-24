package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button goButton;
    private EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        userNameEditText = findViewById(R.id.userNameEditText);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!userNameEditText.getText().toString().trim().isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, Chat.class);
                    intent.putExtra("USERNAME", userNameEditText.getText().toString().trim());
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter user name", Toast.LENGTH_SHORT).show();
                }
              
            }
        });
    }
}