package com.example.book.Model;

import android.text.TextUtils;

import com.example.book.EntityClass.RegisterHelper;
import com.example.book.Presenter.WriteCommentPresenter;
import com.example.book.Tools.Constant;
import com.example.book.Tools.NetworkUtils;
import com.example.book.Tools.UrlHelper;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by ljp on 2017/10/25.
 */

public class WriteCommentModel {
    private WriteCommentPresenter writeCommentPresenter;

    public WriteCommentModel(WriteCommentPresenter writeCommentPresenter) {
        this.writeCommentPresenter = writeCommentPresenter;
    }

    public void addComment(int share_id, String content, int reply_id) {
        if (TextUtils.isEmpty(content)) {
            writeCommentPresenter.failure(Constant.ERROR_LOGIN_NULL);
        } else {
            if (!NetworkUtils.isConnected()) {
                writeCommentPresenter.failure(Constant.ERROR_NO_INTERNET);
            } else {
                try {
                    OkHttpUtils.post().url(UrlHelper.WRITECOMMENT)
                            .addParams("share_id", "" + share_id)
                            .addParams("content", content)
                            .addParams("reply_id", "" + reply_id)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    writeCommentPresenter.failure(1);
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    RegisterHelper registerHelper = new Gson().fromJson(response, RegisterHelper.class);
                                    if (registerHelper.getCode() == 20000) {
                                        writeCommentPresenter.success();
                                    } else {
                                        writeCommentPresenter.failure(2);
                                    }
                                }
                            });
                } catch (JsonIOException j) {
                    j.printStackTrace();
                }
            }
        }
    }
}