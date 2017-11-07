package com.example.book.Model;

import android.util.Log;

import com.example.book.Chat.utils.AppUtil;
import com.example.book.EntityClass.GetListShareHelper;
import com.example.book.EntityClass.GetShareAllHelper;
import com.example.book.EntityClass.GetUserDataHelper;
import com.example.book.EntityClass.UserDataid_Icon;
import com.example.book.Presenter.GetShareAllPresenter;
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
 * Created by ljp on 2017/10/14.
 */

public class GetShareAllModel {
    private List<GetShareAllHelper> shareList = new ArrayList<>();
    private GetShareAllPresenter getShareAllPresenter ;
    private int index = 0; //标志获取用户信息的位置
    private static final String TAG = "GetShareAllModel";
    public GetShareAllModel(GetShareAllPresenter getShareAllPresenter) {
        this.getShareAllPresenter = getShareAllPresenter;
    }

    public void getShare(int page_no, int page_size) {
        if (!NetworkUtils.isConnected()) {
             getShareAllPresenter.failRequestData(Constant.ERROR_NO_INTERNET);
        } else {
            try {
                OkHttpUtils.post()
                        .url(UrlHelper.GETALLSHARE)
                        .addParams("page_no", "" + page_no)
                        .addParams("page_size", "" + page_size)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                getShareAllPresenter.failRequestData(1);
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                AppUtil.saveTime(MyApplication.getContext());
                                GetListShareHelper getListShareHelper = new Gson().fromJson(response, GetListShareHelper.class);
                                if (getListShareHelper.getCode() == 40000) {
                                    getShareAllPresenter.failRequestData(Constant.ERROR_NOMOREUPDATE);
                                }
                                if (getListShareHelper.getCode() == 20000) {
                                    for (int i = 0; i < getListShareHelper.getDataList().size(); i++) {
                                        GetListShareHelper.ShareItem shareItem = getListShareHelper.getDataList().get(i);
                                        GetShareAllHelper getShareAllHelper = new GetShareAllHelper();
                                        getShareAllHelper.setId(shareItem.getId());
                                        getShareAllHelper.setUserId(shareItem.getUserId());
                                        getShareAllHelper.setBookName(shareItem.getName());
                                        getShareAllHelper.setBookcovers(shareItem.getCover());
                                        getShareAllHelper.setContent(shareItem.getContent());
                                        getShareAllHelper.setCommentNum(shareItem.getCommentNum());
                                        getShareAllHelper.setStarNum(shareItem.getStarNum());
                                        getShareAllHelper.setIsbn(shareItem.getIsbn());
                                        getShareAllHelper.setTime( shareItem.getTime());
                                        shareList.add(getShareAllHelper);
                                    }
                                    getOnePisUserData(shareList.get(index).getUserId());
                                    getShareAllPresenter.succeedRequestData(shareList);
                                }
                            }
                        });

            } catch (JsonIOException e) {
                e.printStackTrace();
                getShareAllPresenter.failRequestData(Constant.ERROR_JSONGETWRONG);
            }
        }
    }
    private void getOnePisUserData(int userId){
        try {
            OkHttpUtils.post()
                       .url(UrlHelper.GETUSERINFO)
                       .addParams("user_id",""+userId)
                       .build()
                       .execute(new StringCallback() {
                           @Override
                           public void onError(Call call, Exception e, int id) {
                               getShareAllPresenter.failRequestData(1);
                           }

                           @Override
                           public void onResponse(String response, int id) {
                               GetUserDataHelper getUserDataHelper = new Gson().fromJson(response,GetUserDataHelper.class);
                               UserDataid_Icon userDataid_Icon = new UserDataid_Icon();
                               if(getUserDataHelper.getCode()==20000) {
                                   userDataid_Icon.setUserName(getUserDataHelper.getData().getUsername());
                                   userDataid_Icon.setAvatar(getUserDataHelper.getData().getAvatar());
                               }else if(getUserDataHelper.getCode()==40000){
                                   userDataid_Icon.setUserName("默认名字");
                                   userDataid_Icon.setAvatar(null);
                               }
                               getShareAllPresenter.setUserNameIcon(userDataid_Icon);
                               index ++;
                               if(index < shareList.size()){
                                   getOnePisUserData(shareList.get(index).getUserId());
                               }else {
                                   index = 0;
                               }

                           }
                       });
        }catch (JsonIOException e){
            e.printStackTrace();
            getShareAllPresenter.failRequestData(Constant.ERROR_JSONGETWRONG);
        }
    }
}
