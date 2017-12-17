package com.example.book.Chat.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.book.EntityClass.ChatBook;
import com.example.book.EntityClass.ChatItem;
import com.example.book.EntityClass.ChatObjects;
import com.example.book.Tools.Constant;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public static void saveIsLogin(Context context,boolean isLogin){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.ID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Constant.LOGIN_STATUS,isLogin);
        editor.commit();
    }
    public static long getNowTime(){
           return System.currentTimeMillis();
    }
    public static int getUserid(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.ID,Context.MODE_PRIVATE);
        int userid = sharedPreferences.getInt(Constant.USER_ID,0);
        return  userid;
    }
    public static void setImagePicker(int pictureNum,boolean isMult,boolean isRectangle,int shape){
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setMultiMode(isMult);
        imagePicker.setSelectLimit(pictureNum);
        imagePicker.setSaveRectangle(isRectangle);
        if(shape == Constant.RETCANGLE){
            imagePicker.setStyle(CropImageView.Style.RECTANGLE);
        }else {
            imagePicker.setStyle(CropImageView.Style.CIRCLE);
        }
    }
    public static boolean isExist(String userId){
        List<ChatBook> list1 = DataSupport.where("toUserId = ?", userId).find(ChatBook.class);
         return !(list1.size()==0);
    }
    public static boolean islistExist(String userId){
        List<ChatObjects> list = DataSupport.where("toUserId = ?",userId).find(ChatObjects.class);
        return !(list.size()==0);
    }
    public static List<ChatBook> getChat(String userId){
        List<ChatBook> list1 = DataSupport.where("toUserId = ?", userId).find(ChatBook.class);
       return  list1;
    }
    public static void saveOneChatRecord(String toUserId ,String content,int direction){
        ChatBook chatBook = new ChatBook();
        chatBook.setDirection(direction);
        if(AppUtil.isExist(toUserId)){
            List<ChatBook> list2 = AppUtil.getChat(toUserId);
            chatBook.setId(list2.size()+1);
        }else {
            chatBook.setId(1);
        }
        chatBook.setContent(content);
        chatBook.setToUserId(Integer.parseInt(toUserId));
        chatBook.save();
    }
    public static void deleteChatRecord(int  toUserId){
        DataSupport.deleteAll(ChatBook.class,"toUserId = ?",""+toUserId);
        DataSupport.deleteAll(ChatObjects.class,"toUserId = ?",""+toUserId);
    }
}
