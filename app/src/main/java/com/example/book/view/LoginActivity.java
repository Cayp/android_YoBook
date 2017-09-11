package com.example.book.view;

import android.app.ProgressDialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.book.Base.BaseActivity;
import com.example.book.Presenter.LoginPresenter;
import com.example.book.Presenter.LogoutPresenter;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.LoginHelper;
import com.example.book.Tools.MyToast;
import com.example.book.view.AbstractView.LoginView;
import com.example.book.view.AbstractView.LogoutView;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginView,LogoutView {
    @BindView(R.id.toregister)
    TextView toRegister;
    @BindView(R.id.account)
    EditText inputaccount;
    @BindView(R.id.password)
    EditText inputpassword;
    @BindView(R.id.firstoolbar)
    Toolbar toolbar;
    @BindView(R.id.head)
    TextView headText;
    private LoginPresenter loginPresenter;
    private LogoutPresenter logoutPresenter;
    private String account ;
    private String password ;
    private ProgressDialog progressDialog ;
    private static final String TAG = "LoginActivity";
    @Override
    public void login() {
        account = inputaccount.getText().toString().trim();
        password = inputpassword.getText().toString().trim();
        loginPresenter = new LoginPresenter(this);
        loginPresenter.login(account,password);
    }

    @Override
    public void loginsuccess(LoginHelper loginHelper) {
        hideProgress();

    }

    @Override
    public void loginfailure(int error) {
        switch (error){
            case Constant.ERROR_LOGIN_NULL:
                MyToast.toast("账号或密码不能为空");
                break;
            case Constant.ERROR_LOGIN_WRONG:
                MyToast.toast("账号或密码错误");
                break;
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    toRegister.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);   // 注册字体加下划线
    initActionBar(toolbar,true);
    headText.setText("登录");
    }

    @Override
    protected int setContentViewId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.login,R.id.toregister})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.login:
                login();
                break;
            case R.id.toregister:
                changeActivity(RegisterActivity.class);
                break;
        }
    }
    @Override
    public void logout() {
        logoutPresenter = new LogoutPresenter(this);
        logoutPresenter.logout();
    }

    @Override
    public void logoutSuccess() {
        Log.d(TAG, "退出登陆成功");
    }

    @Override
    public void logoutFailure() {
        Log.d(TAG, "退出登陆失败");
    }

    @Override
    public void showProgress() {
        progressDialog = ProgressDialog.show(this,null,"登录中");
    }

    @Override
    public void hideProgress() {
      progressDialog.dismiss();
    }
}
