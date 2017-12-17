package com.example.book.view.AbstractView;

import android.view.View;

import com.example.book.EntityClass.NoteBook;

import java.util.List;

/**
 * Created by ljp on 2017/11/14.
 */

public interface GetIsbnListener {
    void chooseIsbn(View view , int position, List<NoteBook> noteBookList);
}
