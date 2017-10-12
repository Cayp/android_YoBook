package com.example.book.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.book.Base.BaseFragment;
import com.example.book.Base.LazyLoadFragment;
import com.example.book.Chat.utils.AppUtil;
import com.example.book.EntityClass.GetUserDataHelper;
import com.example.book.Presenter.GetMydataPresenter;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.MyToast;
import com.example.book.Tools.UrlHelper;
import com.example.book.view.AbstractView.GetData;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ljp on 2017/9/8.
 */

public class MyDataFragment extends LazyLoadFragment implements GetData{
    @BindView(R.id.me_headicon)
    CircleImageView  headIcon;
    @BindView(R.id.me_name)
    TextView userName;
    @BindView(R.id.jianjie)
    TextView  synopsis;
    @Override
    public void fetchData() {

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        int currentUserId = AppUtil.getUserid(MyApplication.getContext());
        getdata(currentUserId);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.mydata_fragment;
    }

    @Override
    public void getdata(int userId) {
        GetMydataPresenter getMydataPresenter = new GetMydataPresenter(this);
        getMydataPresenter.getdata(userId);
    }

    @Override
    public void success(GetUserDataHelper getUserDataHelper) {
        Picasso.with(MyApplication.getContext()).load(UrlHelper.GETAVATAR + getUserDataHelper.getData().getUsername() + "/" +getUserDataHelper.getData().getAvatar())
                           .error(R.mipmap.ic_launcher)
                           .into(headIcon);
        userName.setText(getUserDataHelper.getData().getUsername());
        if(getUserDataHelper.getData().getBrief() != null) {
            synopsis.setText(getUserDataHelper.getData().getBrief());
        }
    }

    @Override
    public void failure(int error) {
     switch (error){
         case Constant.ERROR_NO_INTERNET:
             MyToast.toast("无网络连接");
             break;
         case Constant.ERROR_JSONGETWRONG:
             MyToast.toast("Json解析错误");
             break;
         case Constant.ERROR_LOGINFAILURE:
             MyToast.toast("获取个人信息错误");
             break;
     }
    }
}
