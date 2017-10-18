package com.example.book.view.AbstractView;

import com.example.book.EntityClass.GetShareAllHelper;
import com.example.book.EntityClass.UserDataid_Icon;
import com.zhy.http.okhttp.utils.L;

import java.util.List;

/**
 * Created by ljp on 2017/10/14.
 */

public interface GetShare {
    void getShare(int page_no,int page_size);
    void failRequestData(int Error);
    void succeedRequestData(List<GetShareAllHelper> dataList);
    void setUserNameIcon(UserDataid_Icon userNameIcon);

}
