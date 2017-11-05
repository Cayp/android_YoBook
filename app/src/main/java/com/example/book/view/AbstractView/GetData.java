package com.example.book.view.AbstractView;

import com.example.book.EntityClass.GetUserDataHelper;

/**
 * Created by ljp on 2017/10/12.
 */

public interface GetData {
    void getdata(int id );
    void success(GetUserDataHelper.UserData getUserData);
    void failure(int error);
}
