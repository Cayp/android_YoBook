package com.example.book.Tools;

import android.app.Application;
import android.content.Context;

import com.example.book.MyView.PicassoImageLoader;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
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
        Picasso picasso = new Picasso.Builder(MyApplication.getContext())
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();
        Picasso.setSingletonInstance(picasso);
        setPhotoPicker();
    }
    public static Context getContext(){
        return  context;
    }
    private void setPhotoPicker(){                         //仿微信调用图册
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new PicassoImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setFocusHeight(1000);
        imagePicker.setFocusWidth(1000);
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setOutPutX(500);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(500);//保存文件的高度。单位像素
    }
}

