package com.example.book.Chat.presenter;

import com.example.book.Chat.api.Api;
import com.example.book.Chat.entity.Message;
import com.example.book.Chat.module.ChatModule;

import java.util.List;

/**
 * Created by Clanner on 2017/9/13.
 */
public class ChatPresenter implements Api.SendMessage, Api.GetMessage, Api.ReadMessage, Api.ReadAllMessage {

    private Api.SendMessage sm;
    private Api.GetMessage gm;
    private Api.ReadMessage rm;
    private Api.ReadAllMessage ram;
    private ChatModule chatModule;

    public ChatPresenter(Api.SendMessage sm) {
        this.sm = sm;
        chatModule = new ChatModule(this);
    }

    public ChatPresenter(Api.GetMessage gm) {
        this.gm = gm;
        chatModule = new ChatModule(this);
    }

    public ChatPresenter(Api.ReadMessage rm) {
        this.rm = rm;
        chatModule = new ChatModule(this);
    }

    public ChatPresenter(Api.ReadAllMessage ram) {
        this.ram = ram;
        chatModule = new ChatModule(this);
    }

    public ChatPresenter(Api.SendMessage sm, Api.GetMessage gm, Api.ReadMessage rm, Api.ReadAllMessage ram) {
        this.sm = sm;
        this.gm = gm;
        this.rm = rm;
        this.ram = ram;
       chatModule = new ChatModule(this);
    }

    @Override
    public void sendMessage(int to_id, String content) {
        chatModule.sendMessage(to_id, content);
    }

    @Override
    public void sendMessageSuccess() {
        sm.sendMessageSuccess();
    }

    @Override
    public void sendMessageFailure(String message) {
        sm.sendMessageFailure(message);
    }

    @Override
    public void getMessage() {
        chatModule.getMessage();
    }

    @Override
    public void getMessageSuccess(List<Message> messages) {
        gm.getMessageSuccess(messages);
    }

    @Override
    public void getMessageFailure(String message) {
        gm.getMessageFailure(message);
    }

    @Override
    public void readMessage(int from_id) {
        chatModule.readMessage(from_id);
    }

    @Override
    public void readMessageSuccess() {
        rm.readMessageSuccess();
    }

    @Override
    public void readMessageFailure(String message) {
        rm.readMessageFailure(message);
    }

    @Override
    public void readAllMessage() {
        chatModule.readAllMessage();
    }

    @Override
    public void readAllMessageSuccess() {
        ram.readAllMessageSuccess();
    }

    @Override
    public void readAllMessageFailure(String message) {
        ram.readAllMessageFailure(message);
    }
}
