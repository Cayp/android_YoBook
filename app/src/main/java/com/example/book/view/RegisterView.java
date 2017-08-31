package com.example.book.view;

import com.example.book.EntityClass.UserRegisterMsg;

/**
 * Created by ljp on 2017/7/26.
 */

public interface RegisterView {
    void register();
    UserRegisterMsg getData();
    void registerSuccess();
    void registerFailure(int errorId);
    void loadCheckCode();

}

