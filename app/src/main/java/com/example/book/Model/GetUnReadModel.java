package com.example.book.Model;

import com.example.book.Chat.api.Api;
import com.example.book.EntityClass.RegisterHelper;
import com.example.book.EntityClass.UnReadMessageHelper;
import com.example.book.Presenter.GetUnReadPresemter;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by ljp on 2017/11/13.
 */

public class GetUnReadModel {
    private GetUnReadPresemter getUnReadPresemter;

    public GetUnReadModel(GetUnReadPresemter getUnReadPresemter) {
        this.getUnReadPresemter = getUnReadPresemter;
    }

    public void getUnRead(){
        try {
            OkHttpUtils.get().url(Api.GET_MESSAGE_URL)
                             .build()
                             .execute(new StringCallback() {
                                 @Override
                                 public void onError(Call call, Exception e, int id) {

                                 }

                                 @Override
                                 public void onResponse(String response, int id) {
                                     UnReadMessageHelper readMessageHelper = new Gson().fromJson(response,UnReadMessageHelper.class);
                                     if(readMessageHelper.getCode() == 20000){
                                         getUnReadPresemter.success(readMessageHelper.getDataList());
                                         readAllMessage();
                                     }
                                 }
                             });
        }catch (JsonIOException j){
            j.printStackTrace();
        }
    }
    private void readAllMessage(){
        OkHttpUtils.get().url(Api.READ_ALL_MESSAGE_URL)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {


                            }

                            @Override
                            public void onResponse(String response, int id) {
                                RegisterHelper registerHelper = new Gson().fromJson(response,RegisterHelper.class);
                                if(registerHelper.getCode()==20000){

                                }
                            }
                        });
    }
}
