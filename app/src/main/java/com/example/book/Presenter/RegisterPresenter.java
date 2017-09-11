package com.example.book.Presenter;

import com.example.book.EntityClass.UserRegisterMsg;
import com.example.book.Model.RegisterModel;
import com.example.book.view.AbstractView.RegisterView;

/**
 * Created by ljp on 2017/7/26.
 */

public class RegisterPresenter {
    private RegisterView registerView ;
    private RegisterModel registerModel ;

    public RegisterPresenter(RegisterView registerView) {
        this.registerView = registerView;
        registerModel = new RegisterModel(this) ;
    }
    public void register(UserRegisterMsg userRegisterMsg){
        registerModel.register(userRegisterMsg);
    }
    public void registerFailure(int error){
        registerView.registerFailure(error);
    }
    public void registersuccess(){
        registerView.registerSuccess();
        detachView();
    }
    public void detachView(){
        this.registerView = null;
    }
}
