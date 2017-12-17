package com.example.book.view.AbstractView;

import com.example.book.EntityClass.UnReadMessageHelper;

import java.util.List;

/**
 * Created by ljp on 2017/11/13.
 */

public interface GetUnRead {
    void getUnRead();
    void success(List<UnReadMessageHelper.UnReadChats> unReadChats);
    void failure(int error);
 }
