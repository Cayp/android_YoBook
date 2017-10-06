package com.example.book.Tools;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.book.Chat.keepalive.ConnectionService;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by ljp on 2017/7/22.
 */

public class MyApplication extends Application {
    private static Context context;
    private static OkHttpClient okHttpClient;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar)
                .build();
        OkHttpUtils.initClient(okHttpClient);
        SharedPreferences sharedPreferences = getSharedPreferences(Constant.TIME, MODE_PRIVATE);
        if (sharedPreferences != null) {
            long lastTime = sharedPreferences.getLong(Constant.LAST_OPEN, 0);
            if (lastTime != 0 && (System.currentTimeMillis() - lastTime) < 4320) {//登陆未过期，开启长链接
                startService(new Intent(this, ConnectionService.class));
            }
        }
        setId();
    }

    private void setId() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constant.ID, MODE_PRIVATE);
        if (sharedPreferences != null) {
            int id = sharedPreferences.getInt(Constant.USER_ID, 0);
            Constant.currentUserId = id;
        }
    }
}
