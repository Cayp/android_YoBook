package com.example.book.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.book.Base.BaseActivity;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyApplication;

/**
 * Created by ljp on 2017/10/12.
 */

public class WelcomeActivty extends BaseActivity {
    private static final String TAG = "WelcomeActivty";
    @Override
    protected void initView(Bundle savedInstanceState) {
        if(isNeedLogin()){
            changeActivity(LoginActivity.class);
             finish();
        }else {
            changeActivity(MainActivity.class);
             finish();
        }
    }

    @Override
    protected int setContentViewId() {
        return R.layout.activity_welcome;
    }
    public boolean isNeedLogin(){
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(Constant.TIME,MODE_PRIVATE);
        Long lastTime = sharedPreferences.getLong(Constant.LAST_OPEN,0);
        SharedPreferences sharedPreferences1 = MyApplication.getContext().getSharedPreferences(Constant.ID,MODE_PRIVATE);
        boolean isLogin = sharedPreferences1.getBoolean(Constant.LOGIN_STATUS,false);
        if(lastTime != 0 && (System.currentTimeMillis() - lastTime) < Constant.THREEDAY && isLogin){
            return false;
        }else {
            return true;
        }
    }
}
