package com.example.book.view.AbstractView;

import com.example.book.EntityClass.GetUserDataHelper;

/**
 * Created by ljp on 2017/11/2.
 */

public interface UpLoadData {
    void upLoadData(GetUserDataHelper.UserData getUserData);
    void sendSuccess();
    void failure(int error);
}
