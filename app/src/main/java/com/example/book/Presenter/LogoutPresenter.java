package com.example.book.Presenter;

import com.example.book.Model.LogoutModel;
import com.example.book.view.AbstractView.LogoutView;

/**
 * Created by ljp on 2017/7/31.
 */

public class LogoutPresenter{
  private LogoutModel logoutModel;
    private LogoutView logoutView;

    public LogoutPresenter(LogoutView logoutView) {
        this.logoutView = logoutView;
        logoutModel = new LogoutModel(this);
    }

    public void logout(){
        logoutModel.logout();
    }
    public void logoutSuccess(){
        logoutView.logoutSuccess();
    }
    public void logoutFailure(){
        logoutView.logoutFailure();
    }
}
