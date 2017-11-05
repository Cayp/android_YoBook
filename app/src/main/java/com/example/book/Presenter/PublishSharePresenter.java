package com.example.book.Presenter;

import com.example.book.EntityClass.PublishShareHelper;
import com.example.book.Model.PublishShareModel;
import com.example.book.view.AbstractView.PublishShare;

/**
 * Created by ljp on 2017/11/1.
 */

public class PublishSharePresenter implements PublishShare {
    private PublishShare publishShare;
    private PublishShareModel publishShareModel;

    public PublishSharePresenter(PublishShare publishShare) {
        this.publishShare = publishShare;
        publishShareModel = new PublishShareModel(this);
    }

    @Override
    public void publishShare(PublishShareHelper publishShareHelper) {
        publishShareModel.PublishShare(publishShareHelper);
    }

    @Override
    public void publishShareSuccess() {
       publishShare.publishShareSuccess();
    }

    @Override
    public void getFailure(int error) {
       publishShare.getFailure(error);
    }
}
