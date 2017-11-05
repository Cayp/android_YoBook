package com.example.book.view.AbstractView;

import com.example.book.EntityClass.BookDetailHelper;

/**
 * Created by ljp on 2017/11/1.
 */

public interface GetBookDetail {
    void getDetail(String isbn);
    void getSuccess(BookDetailHelper bookDetailHelper);
    void getFailure(int error);
}
