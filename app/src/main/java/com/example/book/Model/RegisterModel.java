package com.example.book.Model;

import android.text.TextUtils;
import com.example.book.EntityClass.RegisterHelper;
import com.example.book.EntityClass.UserRegisterMsg;
import com.example.book.Presenter.RegisterPresenter;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyToast;
import com.example.book.Tools.Mylog;
import com.example.book.Tools.UrlHelper;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.cookie.CookieJarImpl;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Cookie;


/**
 * Created by ljp on 2017/7/26.
 */

public class RegisterModel {
    private static final String TAG = "RegisterModel";
    private RegisterPresenter registerPresenter ;

    public RegisterModel(RegisterPresenter registerPresenter) {
        this.registerPresenter = registerPresenter;
    }
    public void register(UserRegisterMsg userRegisterMsg){
        if (checkdata(userRegisterMsg)){
            Mylog.d(TAG,"进来");
         try{
             OkHttpUtils.post().url(UrlHelper.REGISTER_URL)
                     .addParams("account",userRegisterMsg.getAccount())
                     .addParams("password",userRegisterMsg.getPassword())
                     .addParams("username",userRegisterMsg.getUsername())
                     .addParams("sex",userRegisterMsg.getSex())
                     .addParams("code",userRegisterMsg.getCode()).build().execute(new StringCallback() {
                 @Override
                 public void onError(Call call, Exception e, int id) {
                     registerPresenter.registerFailure(Constant.ERROR_NO_INTERNET);
                 }

                 @Override
                 public void onResponse(String response, int id) {
                   RegisterHelper registerHelper = new Gson().fromJson(response , RegisterHelper.class);
                   int code = registerHelper.getCode();
                     if(code == 40000)
                         MyToast.toast(registerHelper.getMessage());
                     else if(code == 20000){
                         registerPresenter.registersuccess();
                     }
                 }
             });
         }catch (JsonIOException j){
             registerPresenter.registerFailure(Constant.ERROR_JSONGETWRONG);
             j.printStackTrace();
         }
        }
    }
/*
 *     判断数据是否填，两次密码是否一致
 */
    private boolean checkdata(UserRegisterMsg userRegisterMsg){
        if(TextUtils.isEmpty(userRegisterMsg.getAccount())||
            TextUtils.isEmpty(userRegisterMsg.getUsername())||
            TextUtils.isEmpty(userRegisterMsg.getPassword())||
            TextUtils.isEmpty(userRegisterMsg.getCheckpassword())||
            TextUtils.isEmpty(userRegisterMsg.getCode())||
            TextUtils.isEmpty(userRegisterMsg.getSex())){
            registerPresenter.registerFailure(Constant.ERROR_REGISTER_NULL);
            return false ;
        }
        if(!(userRegisterMsg.getCheckpassword().equals(userRegisterMsg.getPassword()))){
            registerPresenter.registerFailure(Constant.ERROR_REGISTER_PASWRDDIFD);
            return false;
        }
        return true ;
    }

}
