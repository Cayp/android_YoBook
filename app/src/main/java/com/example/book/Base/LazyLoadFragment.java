package com.example.book.Base;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.book.Chat.activity.ChatActivity;
import com.example.book.Tools.MyApplication;

/**
 * Created by ljp on 2017/9/9.
 */

public abstract class LazyLoadFragment extends BaseFragment{
    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated;
    public   abstract void  fetchData();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = getUserVisibleHint();
        prepareFetchData();
    }

    public void prepareFetchData(){
        if (isVisibleToUser && isViewInitiated && !isDataInitiated ){
            fetchData();
            isDataInitiated = true;
        }
    }
    public void changeToChat(int userId,String toUserName,String avatar){
        Intent intent = new Intent(MyApplication.getContext(),ChatActivity.class);
        intent.putExtra("toId",userId);
        intent.putExtra("to_Name",toUserName);
        intent.putExtra("toAvatar",avatar);
        startActivity(intent);
    }
}

