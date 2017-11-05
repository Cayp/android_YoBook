package com.example.book.Model;

import android.util.Log;

import com.example.book.Chat.utils.AppUtil;
import com.example.book.EntityClass.GetAllTypeHelper;
import com.example.book.EntityClass.GetUserDataHelper;
import com.example.book.Presenter.GetMydataPresenter;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.NetworkUtils;
import com.example.book.Tools.UrlHelper;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import rx.schedulers.NewThreadScheduler;

/**
 * Created by ljp on 2017/10/12.
 */

public class GetMydataModel {
    private GetMydataPresenter getMydataPresenter;
    private static final String TAG = "GetMydataModel";
    public GetMydataModel(GetMydataPresenter getMydataPresenter) {
        this.getMydataPresenter = getMydataPresenter;
    }

    public void getData(int userid) {
        if (!NetworkUtils.isConnected()) {
            getMydataPresenter.failure(Constant.ERROR_NO_INTERNET);
        } else {
            try {
                Log.d(TAG, "username"+userid);
                OkHttpUtils.post().url(UrlHelper.GETUSERINFO)
                        .addParams(Constant.USER_ID, ""+userid)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                getMydataPresenter.failure(Constant.ERROR_LOGINFAILURE);
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                AppUtil.saveTime(MyApplication.getContext());       //记录请求时间
                                GetUserDataHelper getUserDataHelper = new Gson().fromJson(response , GetUserDataHelper.class);
                                getMydataPresenter.success(getUserDataHelper.getData());
                            }
                        });
            }catch (JsonIOException e){
                e.printStackTrace();
                getMydataPresenter.failure(Constant.ERROR_JSONGETWRONG);
            }
        }
    }
}