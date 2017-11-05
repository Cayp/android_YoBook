package com.example.book.Model;

import android.util.Log;

import com.example.book.EntityClass.RegisterHelper;
import com.example.book.Presenter.UpLoadAvatarPresenter;
import com.example.book.Tools.Constant;
import com.example.book.Tools.NetworkUtils;
import com.example.book.Tools.UrlHelper;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;

/**
 * Created by ljp on 2017/9/18.
 */

public class UploadAvatarModel {
    private static final String TAG = "UploadAvatarModel";
  private UpLoadAvatarPresenter upLoadAvatarPresenter;
    public UploadAvatarModel(UpLoadAvatarPresenter upLoadAvatarPresenter) {
        this.upLoadAvatarPresenter = upLoadAvatarPresenter;
    }
    public void uploadAvatar(String name,File path){
        Log.d(TAG, "touxiang coming");
        if(!NetworkUtils.isConnected()){
      upLoadAvatarPresenter.failure(Constant.ERROR_NO_INTERNET);
        }else {
            try {
                OkHttpUtils.post().url(UrlHelper.UPLOADHEADBIMP)
                                  .addFile("avatar",name,path)
                                  .build()
                                  .execute(new StringCallback() {
                                     @Override
                                     public void onError(Call call, Exception e, int id) {
                                        upLoadAvatarPresenter.failure(1);
                                     }

                                     @Override
                                     public void onResponse(String response, int id) {
                                         RegisterHelper registerHelper = new Gson().fromJson(response,RegisterHelper.class);
                                         Log.d(TAG, "isCode"+registerHelper.getMessage());
                                         if(registerHelper.getCode()==20000){
                                             upLoadAvatarPresenter.success();
                                         }else {

                                         }
                                     }
                                 });
            }catch (JsonIOException j){
                j.printStackTrace();
                upLoadAvatarPresenter.failure(Constant.ERROR_JSONGETWRONG);
            }
        }
    }
}
