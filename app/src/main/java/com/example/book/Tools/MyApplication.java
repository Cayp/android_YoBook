package com.example.book.Tools;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.book.Chat.keepalive.ConnectionService;
import com.example.book.MyView.PicassoImageLoader;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;

import org.litepal.LitePal;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by ljp on 2017/7/22.
 */

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    private static Context context;
    private static OkHttpClient okHttpClient;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePal.initialize(context);
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar)
                .build();
        OkHttpUtils.initClient(okHttpClient);
        Picasso picasso = new Picasso.Builder(MyApplication.getContext())
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();
        Picasso.setSingletonInstance(picasso);
        setPhotoPicker();
        SharedPreferences sharedPreferences = getSharedPreferences(Constant.TIME, MODE_PRIVATE);
        if (sharedPreferences != null) {
            long lastTime = sharedPreferences.getLong(Constant.LAST_OPEN, 0);
            if (lastTime != 0 && (System.currentTimeMillis() - lastTime) < Constant.THREEDAY) {//登陆未过期，开启长链接
                Intent intent = new Intent(this, ConnectionService.class);
                Log.e(TAG, "tartService?" );
                intent.setAction("android.intent.action.RESPOND_VIA_MESSAGE");
                startService(intent);
            }
        }
        setId();
    }
    public static Context getContext(){
        return  context;

    }

    private void setId() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constant.ID, MODE_PRIVATE);
        if (sharedPreferences != null) {
            int id = sharedPreferences.getInt(Constant.USER_ID, 0);
            Constant.currentUserId = id;
        }
    }
    private void setPhotoPicker(){                         //仿微信调用图册
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new PicassoImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮\
        imagePicker.setMultiMode(false);
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(false); //是否按矩形区域保存
        imagePicker.setFocusHeight(1000);
        imagePicker.setFocusWidth(1000);
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setOutPutX(500);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(500);//保存文件的高度。单位像素
    }

}
