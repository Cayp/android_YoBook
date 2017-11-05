package com.example.book.Model;

import com.example.book.EntityClass.BookDetailHelper;
import com.example.book.EntityClass.NotFoundDetailHelper;
import com.example.book.Presenter.GetBookDetailPresenter;
import com.example.book.Tools.Constant;
import com.example.book.Tools.NetworkUtils;
import com.example.book.Tools.UrlHelper;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by ljp on 2017/11/1.
 */

public class GetBookDetailModel {
    private GetBookDetailPresenter getBookDetailPresenter;

    public GetBookDetailModel(GetBookDetailPresenter getBookDetailPresenter) {
        this.getBookDetailPresenter = getBookDetailPresenter;
    }

    public void getBookDetail(String isbn){
        if(!NetworkUtils.isConnected()){
          getBookDetailPresenter.getFailure(Constant.ERROR_NO_INTERNET);
        }else {
            try {
                OkHttpUtils.get().url(UrlHelper.DOUBANINTERFACE +isbn)
                                 .build()
                                 .execute(new StringCallback() {
                                     @Override
                                     public void onError(Call call, Exception e, int id) {
                                         getBookDetailPresenter.getFailure(Constant.CANTFOUNDBOOK);
                                     }

                                     @Override
                                     public void onResponse(String response, int id) {
                                         NotFoundDetailHelper notFoundDetailHelper = new Gson().fromJson(response,NotFoundDetailHelper.class);
                                         BookDetailHelper bookDetailHelper = new Gson().fromJson(response,BookDetailHelper.class);
                                         if(notFoundDetailHelper.getCode()!=6000){
                                             getBookDetailPresenter.getSuccess(bookDetailHelper);
                                         }else {
                                             getBookDetailPresenter.getFailure(Constant.CANTFOUNDBOOK);
                                         }
                                     }
                                 });

            }catch (JsonIOException j){
                j.printStackTrace();
                getBookDetailPresenter.getFailure(Constant.ERROR_JSONGETWRONG);
            }
        }
    }
}
