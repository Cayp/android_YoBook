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
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
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
    private final OkHttpClient okHttpClient = OkHttpUtils.getInstance().getOkHttpClient();
    public PublishShareModel(PublishSharePresenter publishSharePresenter) {
        this.publishSharePresenter = publishSharePresenter;
    }

    public void PublishShare(PublishShareHelper publishShareHelper){
        if(TextUtils.isEmpty(publishShareHelper.getIsbn())||TextUtils.isEmpty(publishShareHelper.getContent())){
           publishSharePresenter.getFailure(Constant.ERROR_LOGIN_NULL);
        }else{
            if(!NetworkUtils.isConnected()){
            publishSharePresenter.getFailure(Constant.ERROR_NO_INTERNET);
            }else {
                   MultipartBody.Builder requestBodybuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                if(publishShareHelper.getFiles()!=null) {
                    for (int i = 0; i < publishShareHelper.getFiles().size(); i++) {
                        requestBodybuilder.addFormDataPart("cover", publishShareHelper.getFiles().get(i).getPath(), RequestBody.create(MediaType.parse("image/jpg"), publishShareHelper.getFiles().get(i).getCover()));
                    }
                }
                   RequestBody requestBody = requestBodybuilder.build();
                    Request request = new Request.Builder()
                                         .url(UrlHelper.PUBLISHSHARE)
                                         .post(requestBody)
                                         .addHeader("content",publishShareHelper.getContent())
                                         .addHeader("name",publishShareHelper.getName())
                                         .addHeader("isbn",publishShareHelper.getIsbn())
                                         .addHeader("type_id",""+publishShareHelper.getTypeId())
                                         .build();
                try {
                   okHttpClient.newCall(request).enqueue(new Callback() {
                       @Override
                       public void onFailure(Call call, IOException e) {
                           publishSharePresenter.getFailure(1);
                       }

                       @Override
                       public void onResponse(Call call, Response response) throws IOException {
                       RegisterHelper registerHelper = new Gson().fromJson(response.body().string(),RegisterHelper.class);
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
