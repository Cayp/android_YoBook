package com.example.book.Presenter;

import com.example.book.EntityClass.PublishSecBookHelper;
import com.example.book.Model.PublishSecBookModel;
import com.example.book.view.AbstractView.PublishSecBook;

/**
 * Created by ljp on 2017/10/4.
 */

public class PublishSecBookPresenter implements PublishSecBook{

    private PublishSecBookModel publishSecBookModel;
    private PublishSecBook publishSecBook;

    public PublishSecBookPresenter(PublishSecBook publishSecBook) {
        this.publishSecBook = publishSecBook;
        publishSecBookModel = new PublishSecBookModel(this);
    }

    @Override
    public void sendData(PublishSecBookHelper publishSecBookHelper) {
    publishSecBookModel.sendData(publishSecBookHelper);
    }

    @Override
    public void sendSuceess() {
    publishSecBook.sendSuceess();
    }

    @Override
    public void sendFailure(int error) {
     publishSecBook.sendFailure(error);
    }

    @Override
    public void showProgress() {
        publishSecBook.showProgress();
    }

    @Override
    public void hideProgress() {
         publishSecBook.hideProgress();
    }
}
