package com.example.specttalkapp_chat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = findViewById(R.id.recyclerView);
        EditText inputMessageEditText = findViewById(R.id.inputMessageEditText);
        Button sendButton = findViewById(R.id.sendButton);

        messageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(messageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chatAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userMessage = inputMessageEditText.getText().toString();
                if (!userMessage.isEmpty()) {
                    sendMessage(userMessage);
                    inputMessageEditText.setText("");
                }
            }
        });
    }

    private void sendMessage(String message) {
        messageList.add(new Message(message, true)); // Сообщение от пользователя
        chatAdapter.notifyDataSetChanged();

        // Ответ бота (простой пример)
        String botResponse = getBotResponse(message);
        messageList.add(new Message(botResponse, false)); // Сообщение от бота
        chatAdapter.notifyDataSetChanged();

        // Прокрутка вниз
        recyclerView.scrollToPosition(messageList.size() - 1);
    }

    private String getBotResponse(String userMessage) {
        // Простейшая логика ответа бота
        if (userMessage.toLowerCase().contains("функционал")) {
            return "Я могу помочь вам с функционалом приложения!";
        } else if (userMessage.toLowerCase().contains("передать оператору")) {
            return "Переключаю вас на реального человека...";
            // Здесь вы можете добавить логику для переключения на реального оператора
        } else {
            return "Извините, я не понимаю ваш вопрос.";
        }
    }
}