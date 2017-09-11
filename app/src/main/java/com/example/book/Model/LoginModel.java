package com.example.book.Model;

import android.text.TextUtils;
import com.example.book.Presenter.LoginPresenter;
import com.example.book.Tools.Constant;
import com.example.book.Tools.LoginHelper;
import com.example.book.Tools.Mylog;
import com.example.book.Tools.UrlHelper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.cookie.CookieJarImpl;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Cookie;

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
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            loginPresenter.loginfailure(Constant.ERROR_LOGIN_NULL);
        } else {
            try {
                loginPresenter.showProgress();
                OkHttpUtils.post().url(UrlHelper.LOGIN_URL)
                                  .addParams("account",account)
                                  .addParams("password",password+"BookCLZJ")
                                  .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        loginPresenter.hideProgress();
                        loginPresenter.loginfailure(Constant.ERROR_LOGIN_WRONG);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        loginPresenter.hideProgress();
                        LoginHelper loginHelper = new Gson().fromJson(response , LoginHelper.class);
                        int code = loginHelper.getCode() ;
                        if(code == 20000){
                         loginPresenter.loginsuccess(loginHelper);
                        }else {
                            loginPresenter.loginfailure(Constant.ERROR_LOGIN_WRONG);
                        }
                    }
                });
            } catch (JsonSyntaxException j){
                loginPresenter.loginfailure(Constant.ERROR_JSONGETWRONG);
                j.printStackTrace();
            }
        }
    }

}
