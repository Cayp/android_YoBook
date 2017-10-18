package com.example.book.Model;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.book.Chat.utils.AppUtil;
import com.example.book.Presenter.LoginPresenter;
import com.example.book.Tools.Constant;
import com.example.book.EntityClass.LoginHelper;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.MyToast;
import com.example.book.Tools.NetworkUtils;
import com.example.book.Tools.UrlHelper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by ljp on 2017/7/20.
 */

public class LoginModel {
    private LoginPresenter loginPresenter ;
    private static final String TAG = "LoginModel";
    public LoginModel(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    public void login(String account , String password) {
        if (!NetworkUtils.isConnected()) {
            loginPresenter.loginfailure(Constant.ERROR_NO_INTERNET);
        } else {
            if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
                loginPresenter.loginfailure(Constant.ERROR_LOGIN_NULL);
            } else {
                try {
                    loginPresenter.showProgress();
                    OkHttpUtils.post().url(UrlHelper.LOGIN_URL)
                            .addParams("account", account)
                            .addParams("password", password + "BookCLZJ")
                            .build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            loginPresenter.hideProgress();
                            loginPresenter.loginfailure(Constant.ERROR_JSONGETWRONG);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            AppUtil.saveTime(MyApplication.getContext());
                            loginPresenter.hideProgress();
                            LoginHelper loginHelper = new Gson().fromJson(response, LoginHelper.class);
                            int code = loginHelper.getCode();
                            if (code == 20000) {
                                loginPresenter.loginsuccess(loginHelper);
                            } else {
                                loginPresenter.loginfailure(Constant.ERROR_LOGIN_WRONG);
                            }
                        }
                    });
                } catch (JsonSyntaxException j) {
                    loginPresenter.loginfailure(Constant.ERROR_JSONGETWRONG);
                    j.printStackTrace();
                }
            }
        }
    }

}
