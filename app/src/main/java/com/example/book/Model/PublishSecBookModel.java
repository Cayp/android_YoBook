package com.example.book.Model;

import android.text.TextUtils;

import com.example.book.EntityClass.PublishSecBookHelper;
import com.example.book.EntityClass.RegisterHelper;
import com.example.book.Presenter.PublishSecBookPresenter;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyToast;
import com.example.book.Tools.UrlHelper;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by ljp on 2017/10/4.
 */

public class PublishSecBookModel {
    private PublishSecBookPresenter publishSecBookPresenter;

    public PublishSecBookModel(PublishSecBookPresenter publishSecBookPresenter) {
        this.publishSecBookPresenter = publishSecBookPresenter;
    }

    public void sendData(PublishSecBookHelper publishSecBookHelper){
    if(checkData(publishSecBookHelper)){
      try{
          OkHttpUtils.post().url(UrlHelper.PUBLISHSECBOOK)
                            .addParams("name",publishSecBookHelper.getBookName())
                            .addParams("description",publishSecBookHelper.getBookDescription())
                            .addParams("type_id",""+publishSecBookHelper.getType_id())
                            .addParams("publish",publishSecBookHelper.getPublishId())
                            .addParams("price",publishSecBookHelper.getPrice())
                            .addFile("cover",publishSecBookHelper.getCoverName(),publishSecBookHelper.getBookCover())
                            .build().execute(new StringCallback() {
              @Override
              public void onError(Call call, Exception e, int id) {
                  publishSecBookPresenter.sendFailure(Constant.ERROR_LOGINFAILURE);
              }

              @Override
              public void onResponse(String response, int id) {
                  RegisterHelper registerHelper = new Gson().fromJson(response,RegisterHelper.class);
                  if(registerHelper.getCode()==20000){
                      publishSecBookPresenter.sendSuceess();
                  }else {
                      publishSecBookPresenter.sendFailure(Constant.ERROR_LOGINFAILURE);
                  }
              }
          });
      }catch (JsonIOException j){
          j.printStackTrace();
          publishSecBookPresenter.sendFailure(Constant.ERROR_JSONGETWRONG);
      }
    }else {
        publishSecBookPresenter.sendFailure(Constant.ERROR_LACKDATA);
    }
    }
    private boolean checkData(PublishSecBookHelper publishSecBookHelper){
        if(TextUtils.isEmpty(publishSecBookHelper.getBookName())||
           TextUtils.isEmpty(publishSecBookHelper.getBookDescription())||
           TextUtils.isEmpty(publishSecBookHelper.getPrice())||
           TextUtils.isEmpty(""+publishSecBookHelper.getType_id())||
           TextUtils.isEmpty(publishSecBookHelper.getCoverName())){
            return false;
        }
        return true;
    }
}
