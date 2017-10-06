package com.example.book.Chat.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.book.Tools.Constant;

/**
 * Created by Clanner on 2017/9/16.
 */
public class AppUtil {
    public static void saveTime(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.TIME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(Constant.LAST_OPEN, System.currentTimeMillis());
        editor.commit();
    }

    public static void saveId(Context context, int user_id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.ID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constant.USER_ID, user_id);
        editor.commit();
    }
}
