package com.example.book.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.book.Base.BaseFragment;
import com.example.book.Base.LazyLoadFragment;
import com.example.book.Chat.utils.AppUtil;
import com.example.book.EntityClass.GetUserDataHelper;
import com.example.book.Model.ModifyUserInfoModel;
import com.example.book.Presenter.GetMydataPresenter;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.MyToast;
import com.example.book.Tools.UrlHelper;
import com.example.book.view.AbstractView.GetData;
import com.example.book.view.ModifyKuangActivity;
import com.example.book.view.ModifyUserDataActivity;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;
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
    @BindView(R.id.me_setting)
    RelativeLayout meSetting;
    private GetUserDataHelper.UserData getUserData;
    private  int currentUserId;
    @Override
    public void fetchData() {

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
         currentUserId = AppUtil.getUserid(MyApplication.getContext());
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
    public void success(GetUserDataHelper.UserData getUserData) {
        this.getUserData = getUserData;
        Picasso.with(MyApplication.getContext()).load(UrlHelper.GETAVATAR  +getUserData.getAvatar())
                            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                           .error(R.mipmap.ic_launcher)
                           .into(headIcon);
        userName.setText(getUserData.getUsername());
        if(!TextUtils.isEmpty(getUserData.getBrief())) {
            synopsis.setText(getUserData.getBrief());
        }else {
            synopsis.setText("暂无简介");
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
    @OnClick({R.id.me_setting})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.me_setting:
                Intent intent = new Intent(MyApplication.getContext(), ModifyUserDataActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("userData",getUserData);
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode == Constant.RESULT_OK) {
                   getdata(currentUserId);
                }
                break;
        }
    }
}
