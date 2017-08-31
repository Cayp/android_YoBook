package com.example.book.Presenter;

import com.example.book.Model.LoginModel;
import com.example.book.Tools.LoginHelper;
import com.example.book.view.LoginView;

/**
 * Created by ljp on 2017/7/21.
 */

public class LoginPresenter {
    private LoginModel loginModel ;
    private LoginView  loginView ;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModel(this) ;
    }

    public void login(String account, String password) {
    loginModel.login(account,password);
    }


    public void loginsuccess(LoginHelper loginHelper) {
     loginView.loginsuccess(loginHelper);
    }


    public void loginfailure(int error) {
     loginView.loginfailure(error);
    }
    public void showProgress(){
        loginView.showProgress();
    }
    public void hideProgress(){
        loginView.hideProgress();
    }
}
