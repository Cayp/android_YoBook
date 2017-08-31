package com.example.book.view;

import com.example.book.Tools.LoginHelper;

/**
 * Created by ljp on 2017/7/29.
 */

public interface LoginView {
    void login();
    void loginsuccess(LoginHelper loginHelper);
    void loginfailure(int error);
    void showProgress();
    void hideProgress();
}
