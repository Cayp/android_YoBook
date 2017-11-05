package com.example.book.Model;

import android.text.TextUtils;
import android.util.Log;

import com.example.book.EntityClass.GetUserDataHelper;
import com.example.book.EntityClass.RegisterHelper;
import com.example.book.EntityClass.UserInfo;
import com.example.book.Presenter.UpLoadUserDataPresenter;
import com.example.book.Tools.Constant;
import com.example.book.Tools.NetworkUtils;
import com.example.book.Tools.UrlHelper;
import com.example.book.view.AbstractView.UpLoadData;
import com.example.book.view.RegisterActivity;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by ljp on 2017/11/2.
 */

public class ModifyUserInfoModel {
    private static final String TAG = "ModifyUserInfoModel";
    private UpLoadUserDataPresenter upLoadUserDataPresenter;

    public ModifyUserInfoModel(UpLoadUserDataPresenter upLoadUserDataPresenter) {
        this.upLoadUserDataPresenter = upLoadUserDataPresenter;
    }

    public void modifyInfo(GetUserDataHelper.UserData getUserData){
        if(TextUtils.isEmpty(getUserData.getUsername())||TextUtils.isEmpty(getUserData.getSex())){
            upLoadUserDataPresenter.failure(Constant.ERROR_LOGIN_NULL);
        }else {
            if (!NetworkUtils.isConnected()) {
                upLoadUserDataPresenter.failure(Constant.ERROR_NO_INTERNET);
            }
            else {
                try {
                    Log.d(TAG, "modifyhahaha"+"com");
                    OkHttpUtils.post()
                               .url(UrlHelper.RESET_BASEMSG_URL)
                               .addParams("username",getUserData.getUsername())
                               .addParams("sex",getUserData.getSex())
                               .addParams("brief",""+getUserData.getBrief())
                               .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                      upLoadUserDataPresenter.failure(1);
                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        RegisterHelper registerHelper = new Gson().fromJson(response,RegisterHelper.class);

                                        if(registerHelper.getCode()==20000){
                                          upLoadUserDataPresenter.sendSuccess();
                                            Log.d(TAG, "modifyhahaha"+registerHelper.getMessage());
                                        }else {
                                            
                                        }
                                    }
                                });
                } catch (JsonIOException j) {
                    j.printStackTrace();
                    upLoadUserDataPresenter.failure(Constant.ERROR_JSONGETWRONG);
                }
            }
        }

    }
}
