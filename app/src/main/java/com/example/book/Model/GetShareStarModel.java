package com.example.book.Model;

import com.example.book.EntityClass.GetAllTypeHelper;
import com.example.book.EntityClass.GetShareStarHelper;
import com.example.book.Presenter.GetShareStarPresenter;
import com.example.book.Tools.Constant;
import com.example.book.Tools.NetworkUtils;
import com.example.book.Tools.UrlHelper;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by ljp on 2017/10/26.
 */

public class GetShareStarModel {
    private GetShareStarPresenter getShareStarPresenter;

    public GetShareStarModel(GetShareStarPresenter getShareStarPresenter) {
        this.getShareStarPresenter = getShareStarPresenter;
    }

    public void getShareStar(int share_id){
        if(!NetworkUtils.isConnected()){
          getShareStarPresenter.getShareStarFailure(Constant.ERROR_NO_INTERNET);
        }else {
            try {
                OkHttpUtils.post().url(UrlHelper.GETSHARESTAR)
                                  .addParams("share_id",""+share_id)
                                  .build()
                                  .execute(new StringCallback() {
                                      @Override
                                      public void onError(Call call, Exception e, int id) {
                                          getShareStarPresenter.getShareStarFailure(1);
                                      }

                                      @Override
                                      public void onResponse(String response, int id) {
                                          GetShareStarHelper getShareStarHelper = new Gson().fromJson(response, GetShareStarHelper.class);
                                         if(getShareStarHelper.getCode()==20000){
                                             getShareStarPresenter.getShareStarsuccess(getShareStarHelper.getDataList());
                                         }else if (getShareStarHelper.getCode()==40000){
                                             getShareStarPresenter.getShareStarFailure(Constant.ERROR_LOGIN_NULL);
                                         }
                                      }
                                  });
            }catch (JsonIOException j){
                j.printStackTrace();
            }
        }
    }
}
