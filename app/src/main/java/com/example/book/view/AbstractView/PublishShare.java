package com.example.book.view.AbstractView;

import com.example.book.EntityClass.PublishShareHelper;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by ljp on 2017/11/1.
 */

public interface PublishShare {
    void publishShare(PublishShareHelper publishShareHelper);
    void publishShareSuccess();
    void getFailure(int error);
}
