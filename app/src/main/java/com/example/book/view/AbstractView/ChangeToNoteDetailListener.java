package com.example.book.view.AbstractView;

import android.view.View;

import com.example.book.EntityClass.Note;

import java.util.List;

/**
 * Created by ljp on 2017/11/14.
 */

public interface ChangeToNoteDetailListener {
    void changeTo(View view , String isbn,int id);
}
