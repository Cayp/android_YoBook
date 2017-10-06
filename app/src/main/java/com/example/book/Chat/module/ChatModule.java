package com.example.book.Chat.module;

import com.example.book.Chat.api.Api;
import com.example.book.Chat.entity.Message;
import com.example.book.Chat.entity.Result;
import com.example.book.Chat.presenter.ChatPresenter;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Clanner on 2017/9/13.
 */
public class ChatModule {
    private ChatPresenter chatPresenter;

    public ChatModule(ChatPresenter chatPresenter) {
        this.chatPresenter = chatPresenter;
    }

    /**
     * 发送信息
     */
    public void sendMessage(int to_id, String content) {
        OkHttpUtils.post().url(Api.SEND_MESSAGE_URL)
                .addParams("to_id", "" + to_id)
                .addParams("content", content)
                .build().execute(new Callback<Result>() {
            @Override
            public Result parseNetworkResponse(Response response, int id) throws Exception {
                return new Gson().fromJson(response.body().string(), Result.class);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                chatPresenter.sendMessageFailure("发送消息失败，请检查网络连接");
            }

            @Override
            public void onResponse(Result result, int id) {
                if (result.getCode() == Api.SUCCESS) {
                    chatPresenter.sendMessageSuccess();
                } else {
                    chatPresenter.sendMessageFailure(result.getMessage());
                }
            }
        });
    }

    /**
     * 获取信息
     */
    public void getMessage() {
        OkHttpUtils.get().url(Api.GET_MESSAGE_URL).build().execute(new Callback<Result<Object, Message>>() {
            @Override
            public Result<Object, Message> parseNetworkResponse(Response response, int id) throws Exception {
                return new Gson().fromJson(response.body().string(), Result.class);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                chatPresenter.getMessageFailure("获取消息失败，请检查网络连接");
            }

            @Override
            public void onResponse(Result<Object, Message> result, int id) {
                if (result.getCode() == Api.SUCCESS) {
                    chatPresenter.getMessageSuccess(result.getDataList());
                } else {
                    chatPresenter.getMessageFailure(result.getMessage());
                }
            }
        });
    }

    /**
     * 已读消息
     */
    public void readMessage(int from_id) {
        OkHttpUtils.post().url(Api.READ_MESSAGE_URL)
                .addParams("from_id", "" + from_id).build().execute(new Callback<Result>() {
            @Override
            public Result parseNetworkResponse(Response response, int id) throws Exception {
                return new Gson().fromJson(response.body().string(), Result.class);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                chatPresenter.readMessageFailure("消息已读失败，请检查网络连接");
            }

            @Override
            public void onResponse(Result result, int id) {
                if (result.getCode() == Api.SUCCESS) {
                    chatPresenter.readMessageSuccess();
                } else {
                    chatPresenter.readMessageFailure(result.getMessage());
                }
            }
        });
    }

    /**
     * 已读所有消息
     */
    public void readAllMessage() {
        OkHttpUtils.get().url(Api.READ_ALL_MESSAGE_URL).build().execute(new Callback<Result>() {
            @Override
            public Result parseNetworkResponse(Response response, int id) throws Exception {
                return new Gson().fromJson(response.body().string(), Result.class);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                chatPresenter.readAllMessageFailure("已读所有消息失败，请检查网络连接");
            }

            @Override
            public void onResponse(Result result, int id) {
                if (result.getCode() == Api.SUCCESS) {
                    chatPresenter.readAllMessageSuccess();
                } else {
                    chatPresenter.readAllMessageFailure(result.getMessage());
                }
            }
        });
    }
}
