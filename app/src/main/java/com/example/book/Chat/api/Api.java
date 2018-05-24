package com.example.book.Chat.api;

import com.example.book.Chat.entity.Message;

import java.util.List;

/**
 * Created by Clanner on 2017/9/13.
 */
public interface Api {

    String BASE_URL = "http://119.29.143.189/Book";
    String SEND_MESSAGE_URL = BASE_URL + "Home/Chat/sendMessage";
    String GET_MESSAGE_URL = BASE_URL + "Home/Chat/getUnReadMessage";
    String READ_MESSAGE_URL = BASE_URL + "Home/Chat/readMessage";
    String READ_ALL_MESSAGE_URL = BASE_URL + "Home/Chat/readAllMessage";
    int SUCCESS = 20000;

    interface SendMessage {
        void sendMessage(int to_id, String content);

        void sendMessageSuccess();

        void sendMessageFailure(String message);
    }

    interface GetMessage {
        void getMessage();

        void getMessageSuccess(List<Message> messages);

        void getMessageFailure(String message);
    }

    interface ReadMessage {
        void readMessage(int from_id);

        void readMessageSuccess();

        void readMessageFailure(String message);
    }

    interface ReadAllMessage {
        void readAllMessage();

        void readAllMessageSuccess();

        void readAllMessageFailure(String message);
    }
}
