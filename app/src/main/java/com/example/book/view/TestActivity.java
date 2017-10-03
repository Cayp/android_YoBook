package com.example.book.view;

import android.os.Bundle;
import android.util.Log;

import com.example.book.Base.BaseActivity;
import com.example.book.EntityClass.GetAllTypeHelper;
import com.example.book.R;
import com.example.book.Tools.UrlHelper;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by ljp on 2017/9/28.
 */

public class TestActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        getType();
    }

    @Override
    protected int setContentViewId() {
        return R.layout.publish;
    }
    private static final String TAG = "GetAllTypeModel";
    public void getType(){
        OkHttpUtils.get().url(UrlHelper.GETALLTYPE).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                GetAllTypeHelper getAllTypeHelper = new Gson().fromJson(response,GetAllTypeHelper.class);
                if(getAllTypeHelper.getCode()==20000){
                    for(GetAllTypeHelper.TypeData typeData : getAllTypeHelper.getDataList()){
                        Log.d(TAG, "alltype"+typeData.getId()+typeData.getName());
                    }
                }
            }
        });
    }
}
