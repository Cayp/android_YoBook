package com.example.book.Tools;

import android.widget.Toast;

import com.example.book.Tools.MyApplication;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by ljp on 2017/7/22.
 */

public class MyToast  {
    public static void toast(String text){
        Toast.makeText(MyApplication.getContext(),text,LENGTH_SHORT).show();
    }
}
