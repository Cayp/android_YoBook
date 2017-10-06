package com.example.book.Tools;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.lzy.imagepicker.util.Utils;

/**
 * Created by ljp on 2017/10/6.
 */

public class NetworkUtils {
    private  static NetworkInfo getActiveNetworkInfo(){
        ConnectivityManager cm = (ConnectivityManager) MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return  cm.getActiveNetworkInfo();
    }

    /**
     * 判断是否网络连接
     * @return
     */
     public static boolean isConnected(){
         NetworkInfo info =getActiveNetworkInfo();
         return info!=null&&info.isConnected();
     }

}

