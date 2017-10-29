package com.example.book.Model;

import android.util.Log;

import com.example.book.Chat.utils.AppUtil;
import com.example.book.EntityClass.GetCommentHelper;
import com.example.book.EntityClass.GetUserDataHelper;
import com.example.book.EntityClass.UserDataid_Icon;
import com.example.book.Presenter.GetCommentPresenter;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.NetworkUtils;
import com.example.book.Tools.UrlHelper;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by ljp on 2017/10/23.
 */

public class GetCommentModel {
    private GetCommentPresenter getCommentPresenter;
    private List<GetCommentHelper.CommentItem> list =  new ArrayList<>();
    private int index  = 0;//标志位
    public GetCommentModel(GetCommentPresenter getCommentPresenter) {
        this.getCommentPresenter = getCommentPresenter;
    }
    public void GetComment(int share_id,int page_no,int page_size){
        if(!NetworkUtils.isConnected()){
            getCommentPresenter.getCommentFailure(Constant.ERROR_NO_INTERNET);
        }else{
            try{
                OkHttpUtils.post()
                           .url(UrlHelper.GETCOMMENT)
                           .addParams("share_id",""+share_id)
                           .addParams("page_no",""+page_no)
                           .addParams("page_size",""+page_size)
                           .build()
                           .execute(new StringCallback() {
                               @Override
                               public void onError(Call call, Exception e, int id) {

                               }

                               @Override
                               public void onResponse(String response, int id) {
                                   AppUtil.saveTime(MyApplication.getContext());
                                   GetCommentHelper getCommentHelper = new Gson().fromJson(response,GetCommentHelper.class);
                                   if(getCommentHelper.getCode() == 20000) {
                                       list.addAll(getCommentHelper.getDataList());
                                       getCommentPresenter.getCommentSucceed(list);
                                       getUserPisData(list.get(index).getUserId());
                                   }else if(getCommentHelper.getCode() == 40000)
                                       getCommentPresenter.getCommentFailure(2);
                               }
                           });
            }catch (JsonIOException e){
                e.printStackTrace();
                getCommentPresenter.getCommentFailure(Constant.ERROR_JSONGETWRONG);
            }

        }
    }
    private void getUserPisData(int useId){
        try {
            OkHttpUtils.post().url(UrlHelper.GETUSERINFO).addParams("user_id",""+useId).build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    AppUtil.saveTime(MyApplication.getContext());
                    GetUserDataHelper getUserDataHelper = new Gson().fromJson(response, GetUserDataHelper.class);
                    UserDataid_Icon userDataid_Icon = new UserDataid_Icon();
                    if(getUserDataHelper.getCode()==20000) {
                        userDataid_Icon.setUserName(getUserDataHelper.getData().getUsername());
                        userDataid_Icon.setAvatar(getUserDataHelper.getData().getAvatar());
                    }else if(getUserDataHelper.getCode()==40000){
                        userDataid_Icon.setUserName("默认名字");
                        userDataid_Icon.setAvatar(null);
                    }
                    getCommentPresenter.getUserData(userDataid_Icon);
                    index++;
                    if(index < list.size()) {
                        getUserPisData(list.get(index).getUserId());
                    }else { index = 0;}               //判断是否请求完

                }
            });
        }catch (JsonIOException j){
            j.printStackTrace();
        }
    }
}

