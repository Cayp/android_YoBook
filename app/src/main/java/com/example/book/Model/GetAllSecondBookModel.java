package com.example.book.Model;

import android.util.Log;

import com.example.book.EntityClass.GetUserDataHelper;
import com.example.book.EntityClass.SecondBookAllData;
import com.example.book.EntityClass.ShowBook_TardeHelper;
import com.example.book.EntityClass.UserDataid_Icon;
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
    private ShowBook_TardeHelper showBook_tardeHelper;
    private List<SecondBookAllData> list = new ArrayList<SecondBookAllData>();
    private GetBookPresenter getBookPresenter;
    private static final String TAG = "GetAllSecondBookModel";

    public GetAllSecondBookModel(GetBookPresenter getBookPresenter) {
        this.getBookPresenter = getBookPresenter;
    }


    public void loadData(int page_no,int page_size,int typeId){
        try{
            if(typeId != 0){
                OkHttpUtils.post().url(UrlHelper.GETSECONDBOOKBYTYPE)
                        .addParams("type_id",""+typeId)
                        .addParams("page_no", "" + page_no)
                        .addParams("page_size", "" + page_size)
                        .build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            getBookPresenter.failRequestData(1);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            showBook_tardeHelper = new Gson().fromJson(response, ShowBook_TardeHelper.class);
                            if (showBook_tardeHelper.getCode() == 40000) {
                                getBookPresenter.failRequestData(Constant.ERROR_NOMOREUPDATE);
                            }
                            if (showBook_tardeHelper.getCode() == 20000) {
                                for (int i = 0; i < showBook_tardeHelper.getDataList().size(); i++) {
                                    ShowBook_TardeHelper.BookData bookData = showBook_tardeHelper.getDataList().get(i);
                                    SecondBookAllData secondBookAllData = new SecondBookAllData();
                                    secondBookAllData.setBookId(bookData.getId());
                                    secondBookAllData.setUserId(bookData.getUserId());
                                    secondBookAllData.setBookCover(bookData.getCover());
                                    secondBookAllData.setBookName(bookData.getName());
                                    secondBookAllData.setPrice(bookData.getPrice());
                                    secondBookAllData.setTypedId(bookData.getTypeId());
                                    secondBookAllData.setDescription(bookData.getDescription());
                                    getUseTwoPisData(secondBookAllData.getUserId());
                                    Log.d(TAG, "fuckuserid" + secondBookAllData.getUserId());
                                    list.add(secondBookAllData);
                                }
                                getBookPresenter.succeedRequestData(list);
                            }
                        }
                    });
            }
            else {
                OkHttpUtils.post().url(UrlHelper.GETALLSECONDBOOK).addParams("page_no", "" + page_no)
                        .addParams("page_size", "" + page_size)
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getBookPresenter.failRequestData(1);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        showBook_tardeHelper = new Gson().fromJson(response, ShowBook_TardeHelper.class);
                        if (showBook_tardeHelper.getCode() == 40000) {
                            getBookPresenter.failRequestData(Constant.ERROR_NOMOREUPDATE);
                            Log.d(TAG, "page2" + showBook_tardeHelper.getMessage());
                        }
                        if (showBook_tardeHelper.getCode() == 20000) {
                            for (int i = 0; i < showBook_tardeHelper.getDataList().size(); i++) {
                                ShowBook_TardeHelper.BookData bookData = showBook_tardeHelper.getDataList().get(i);
                                SecondBookAllData secondBookAllData = new SecondBookAllData();
                                secondBookAllData.setBookId(bookData.getId());
                                secondBookAllData.setUserId(bookData.getUserId());
                                secondBookAllData.setBookCover(bookData.getCover());
                                secondBookAllData.setBookName(bookData.getName());
                                secondBookAllData.setPrice(bookData.getPrice());
                                secondBookAllData.setTypedId(bookData.getTypeId());
                                secondBookAllData.setDescription(bookData.getDescription());
                                getUseTwoPisData(secondBookAllData.getUserId());
                                Log.d(TAG, "fuckuserid" + secondBookAllData.getUserId());
                                list.add(secondBookAllData);
                            }
                            getBookPresenter.succeedRequestData(list);
                        }
                    }
                });
            }
        }catch (JsonIOException e){

            e.printStackTrace();
        }
    }
    public void getUseTwoPisData(int user_id) {
        try {

            OkHttpUtils.post().url(UrlHelper.GETUSERINFO).addParams("user_id",""+user_id).build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.d(TAG, "nodata");
                }

                @Override
                public void onResponse(String response, int id) {
                    GetUserDataHelper getUserDataHelper = new Gson().fromJson(response, GetUserDataHelper.class);
                    UserDataid_Icon userDataid_Icon = new UserDataid_Icon();
                        if(getUserDataHelper.getCode()==20000) {
                            userDataid_Icon.setUserName(getUserDataHelper.getData().getUsername());
                            userDataid_Icon.setAvatar(getUserDataHelper.getData().getAvatar());
                        }else if(getUserDataHelper.getCode()==40000){
                            userDataid_Icon.setUserName("默认名字");
                            userDataid_Icon.setAvatar(null);
                        }
                        getBookPresenter.setUserIdIcon(userDataid_Icon);
                    Log.d(TAG, "isEmpty"+userDataid_Icon.getUserName());

                }
            });
        } catch (JsonIOException e) {
            e.printStackTrace();
        }
    }

}
