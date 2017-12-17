package com.example.book.Presenter;

import com.example.book.EntityClass.UnReadMessageHelper;
import com.example.book.Model.GetUnReadModel;
import com.example.book.view.AbstractView.GetUnRead;

import java.util.List;

/**
 * Created by ljp on 2017/11/13.
 */

public class GetUnReadPresemter implements GetUnRead {
    private GetUnRead getUnRead;
    private GetUnReadModel getUnReadModel;

    public GetUnReadPresemter(GetUnRead getUnRead) {
        this.getUnRead = getUnRead;
        getUnReadModel = new GetUnReadModel(this);
    }

    @Override
    public void getUnRead() {
        getUnReadModel.getUnRead();
    }

    @Override
    public void success(List<UnReadMessageHelper.UnReadChats> unReadChats) {
        getUnRead.success(unReadChats);
    }

    @Override
    public void failure(int error) {
        getUnRead.failure(error);
    }
}
