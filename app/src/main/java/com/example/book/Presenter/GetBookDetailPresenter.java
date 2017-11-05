package com.example.book.Presenter;

import com.example.book.EntityClass.BookDetailHelper;
import com.example.book.Model.GetBookDetailModel;
import com.example.book.view.AbstractView.GetBookDetail;

/**
 * Created by ljp on 2017/11/1.
 */

public class GetBookDetailPresenter implements GetBookDetail {
    private GetBookDetail getBookDetail;
    private GetBookDetailModel getBookDetailModel;

    public GetBookDetailPresenter(GetBookDetail getBookDetail) {
        this.getBookDetail = getBookDetail;
        getBookDetailModel = new GetBookDetailModel(this);
    }

    @Override
    public void getDetail(String isbn) {
    getBookDetailModel.getBookDetail(isbn);
    }

    @Override
    public void getSuccess(BookDetailHelper bookDetailHelper) {
     getBookDetail.getSuccess(bookDetailHelper);
    }

    @Override
    public void getFailure(int error) {
     getBookDetail.getFailure(error);
    }
}
