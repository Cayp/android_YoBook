package com.example.book.Model;

import android.text.TextUtils;
import android.util.Log;

import com.example.book.EntityClass.PublishShareHelper;
import com.example.book.EntityClass.RegisterHelper;
import com.example.book.Presenter.PublishSharePresenter;
import com.example.book.Tools.Constant;
import com.example.book.Tools.NetworkUtils;
import com.example.book.Tools.UrlHelper;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by ljp on 2017/11/1.
 */

public class PublishShareModel {
    private static final String TAG = "PublishShareModel";
    private PublishSharePresenter publishSharePresenter;
    public PublishShareModel(PublishSharePresenter publishSharePresenter) {
        this.publishSharePresenter = publishSharePresenter;
    }

    public void PublishShare(final PublishShareHelper publishShareHelper){
        if(TextUtils.isEmpty(publishShareHelper.getIsbn())||TextUtils.isEmpty(publishShareHelper.getContent())){
           publishSharePresenter.getFailure(Constant.ERROR_LOGIN_NULL);
        }else{
            if(!NetworkUtils.isConnected()){
            publishSharePresenter.getFailure(Constant.ERROR_NO_INTERNET);
            }else {
                try {
                PostFormBuilder postFormBuilder = OkHttpUtils.post();
                if(publishShareHelper.getFiles()!=null) {
                    for (int i = 0; i < publishShareHelper.getFiles().size(); i++) {
                        postFormBuilder.addFile("cover"
                                , publishShareHelper.getFiles().get(i).getPath()
                                , publishShareHelper.getFiles().get(i).getCover());
                    }
                }
                    postFormBuilder.url(UrlHelper.PUBLISHSHARE)
                                   .addParams("content",publishShareHelper.getContent())
                                   .addParams("name",publishShareHelper.getName())
                                   .addParams("isbn",publishShareHelper.getIsbn())
                                    .addParams("type_id",""+publishShareHelper.getTypeId())
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            publishSharePresenter.getFailure(1);
                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                        RegisterHelper registerHelper = new Gson().fromJson(response,RegisterHelper.class);
                                            Log.e("Zxing","coming?"+registerHelper.getCode())    ;
                                            if(registerHelper.getCode()==20000){
                                             publishSharePresenter.publishShareSuccess();
                                         }
                                        }
                                    });
                    
                }catch (JsonIOException j){
                    j.printStackTrace();
                }
            }
        }
    }
}
