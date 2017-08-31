package com.example.book.view;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.book.Base.BaseActivity;
import com.example.book.Presenter.RegisterPresenter;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.MyToast;
import com.example.book.Tools.Mylog;
import com.example.book.Tools.UrlHelper;
import com.example.book.EntityClass.UserRegisterMsg;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterView,View.OnClickListener{
    private static final String TAG = "RegisterActivity";
    RegisterPresenter registerPresenter ;
    @BindView(R.id.checkcodeView)
    ImageView imageView;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.account)
    EditText account;
    @BindView(R.id.username)
    EditText username ;
    @BindView(R.id.checkcode)
    EditText checkcode ;
    @BindView(R.id.male)
    RadioButton  male ;
    @BindView(R.id.female)
    RadioButton female ;
    @BindView(R.id.group)
    RadioGroup group ;
    @BindView(R.id.register)
    Button regiseter ;
    @BindView(R.id.passwordcheck)
    EditText checkpassword ;
    @BindView(R.id.firstoolbar)
    Toolbar toolbar ;
    @BindView(R.id.head)
    TextView headText ;
    String sex = "";
    @Override
    protected void initView(Bundle savedInstanceState)
    {
        loadCheckCode();  //加载验证码
        initActionBar(toolbar,true);
        headText.setText("注册");
    }

    @OnClick({R.id.female,R.id.male,R.id.register,R.id.checkcodeView})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.register:
                register();
                break;
            case R.id.female:
                sex = "f";
                break;
            case R.id.male:
                sex = "m";
                break;
            case R.id.checkcodeView:
                loadCheckCode();
                break;
        }
    }
    @Override
    public UserRegisterMsg getData(){
        UserRegisterMsg userRegisterMsg = new UserRegisterMsg();
        userRegisterMsg.setAccount(account.getText().toString().trim());
        userRegisterMsg.setUsername(username.getText().toString().trim());
        userRegisterMsg.setPassword(password.getText().toString().trim());
        userRegisterMsg.setCode(checkcode.getText().toString().trim());
        userRegisterMsg.setCheckpassword(checkpassword.getText().toString().trim());
        userRegisterMsg.setSex(sex);
        return userRegisterMsg;
    }

    @Override
    protected int setContentViewId() {
        return R.layout.activity_register;
    }
    @Override
    public void  loadCheckCode() {
        Picasso.with(this).load(UrlHelper.CHECKCODE_URL)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)                //不缓存在硬盘
                .into(imageView);
    }
    @Override
    public void registerSuccess() {
     MyToast.toast("注册成功");
    }

    @Override
    public void registerFailure(int errorId) {
        loadCheckCode();
    switch (errorId){
        case Constant.ERROR_REGISTER_NULL:
            MyToast.toast("必填项不能为空");
            break ;
        case Constant.ERROR_CHECKCODE_CANTGET:
            MyToast.toast("获取验证码失败");
            break;
        case Constant.ERROR_REGISTER_PASWRDDIFD:
            MyToast.toast("两次输入的密码不相同");
            break;
        case Constant.ERROR_REGISTER_FAILURE:
            MyToast.toast("注册失败，账户或用户名已被注册或验证码错误");
            break;
        case Constant.ERROR_JSONGETWRONG:
            MyToast.toast("json解析异常");
            break;
    }
    }

    @Override
    public void register() {
        if(registerPresenter == null){
            registerPresenter = new RegisterPresenter(this) ;
        }
        registerPresenter.register(getData());
    }
}
