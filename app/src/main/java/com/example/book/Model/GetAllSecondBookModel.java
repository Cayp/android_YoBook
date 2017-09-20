package com.example.book.Model;

import com.example.book.EntityClass.GetUserDataHelper;
import com.example.book.EntityClass.SecondBookAllData;
import com.example.book.EntityClass.ShowBook_TardeHelper;
import com.example.book.Presenter.GetBookPresenter;
import com.example.book.Tools.Constant;
import com.example.book.Tools.UrlHelper;
import com.example.book.view.AbstractView.PagingLoad;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by ljp on 2017/9/18.
 */

public class GetAllSecondBookModel {
    private String userName ;
    private String iconPath;
    private ShowBook_TardeHelper showBook_tardeHelper;
    private List<SecondBookAllData> list = new ArrayList<SecondBookAllData>();
    private GetBookPresenter getBookPresenter;

    public GetAllSecondBookModel(GetBookPresenter getBookPresenter) {
        this.getBookPresenter = getBookPresenter;
    }


    public void loadData(int page_no,int page_size){
        try{
            OkHttpUtils.post().url(UrlHelper.GETALLSECONDBOOK).addParams("page_no",""+page_no)
                                                              .addParams("page_no",""+page_size)
                                                              .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                  getBookPresenter.failRequestData(1);
                }

                @Override
                public void onResponse(String response, int id) {
                   showBook_tardeHelper = new Gson().fromJson(response,ShowBook_TardeHelper.class);
                    if(showBook_tardeHelper.getCode()==20000){
                     for(int i = 0;i <= (showBook_tardeHelper.getDataList().size()-1);i ++){
                         ShowBook_TardeHelper.BookData bookData =  showBook_tardeHelper.getDataList().get(i);
                         getUseTwoPisData( bookData.getUserId());
                         SecondBookAllData secondBookAllData = new SecondBookAllData();
                         secondBookAllData.setBookId(bookData.getId());
                         secondBookAllData.setUserId(bookData.getUserId());
                         secondBookAllData.setBookCover(bookData.getCover());
                         secondBookAllData.setBookName(bookData.getName());
                         secondBookAllData.setDescription(bookData.getDescription());
                         secondBookAllData.setAvatar(iconPath);
                         secondBookAllData.setUserName(userName);
                         list.add(secondBookAllData);
                         iconPath = null ;
                         userName = null ;
                     }
                     getBookPresenter.succeedRequestData(list);
                    }else {
                        getBookPresenter.failRequestData(Constant.ERROR_NOMOREUPDATE);
                    }
                }
            });
        }catch (JsonIOException e){
            e.printStackTrace();
        }
    }
    private void getUseTwoPisData(final int user_id){
        try {
            OkHttpUtils.post().url(UrlHelper.GETUSERINFO).addParams("user_id",""+user_id).build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    GetUserDataHelper getUserDataHelper = new Gson().fromJson(response,GetUserDataHelper.class);
                     if(getUserDataHelper.getCode()!= 20000){
                         iconPath = getUserDataHelper.getData().getAvatar();
                         userName = getUserDataHelper.getData().getUsername();
                     }
                }
            });
        }catch (JsonIOException e ){
         e.printStackTrace();
        }
    }

}
