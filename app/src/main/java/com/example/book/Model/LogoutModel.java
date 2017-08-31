package com.example.book.Model;

import com.example.book.Presenter.LogoutPresenter;
import com.example.book.Tools.Constant;
import com.example.book.Tools.LogoutHelper;
import com.example.book.Tools.UrlHelper;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by ljp on 2017/7/31.
 */

public class LogoutModel {
    private LogoutPresenter logoutPresenter;
    public LogoutModel(LogoutPresenter logoutPresenter) {
        this.logoutPresenter = logoutPresenter;
    }
    public void logout(){
        OkHttpUtils.get().url(UrlHelper.LOGOUT_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                logoutPresenter.logoutFailure();
            }
            @Override
            public void onResponse(String response, int id) {
                LogoutHelper helper = new Gson().fromJson(response,LogoutHelper.class);
                if (helper.getCode() == 20000){
                    logoutPresenter.logoutSuccess();
                }else {
                    logoutPresenter.logoutFailure();
                }
            }
        });
    }
}
