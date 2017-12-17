package com.example.book.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.book.Adapter.MyNoteBookAdapter;
import com.example.book.Base.BaseActivity;
import com.example.book.EntityClass.Note;
import com.example.book.EntityClass.NoteBook;
import com.example.book.R;
import com.example.book.Tools.MyApplication;
import com.example.book.view.AbstractView.GetIsbnListener;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ljp on 2017/11/13.
 */

public class MyNote extends BaseActivity {
    @BindView(R.id.myNoteToolbar)
    Toolbar toolbar;
    @BindView(R.id.noteCount)
    TextView noteCount;
    @BindView(R.id.bookCount)
    TextView bookCount;
    @BindView(R.id.recycler)
    RecyclerView showBookList;

    private List<NoteBook> noteBooks ;
    private GetIsbnListener getIsbnListener = new GetIsbnListener() {
        @Override
        public void chooseIsbn(View view, int position, List<NoteBook> noteBookList) {
            Intent intent = new Intent(MyNote.this,NoteItemBookActivity.class);
            intent.putExtra("bookName",noteBookList.get(position).getName());
            intent.putExtra("isbn",noteBookList.get(position).getIsbn());
            startActivity(intent);
        }
    };
    @Override
    protected void initView(Bundle savedInstanceState) {
      initActionBar(toolbar,true);
        setCount();
        MyNoteBookAdapter noteBookAdapter = new MyNoteBookAdapter();
        noteBookAdapter.setNoteBooks(noteBooks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApplication.getContext());
        noteBookAdapter.setGetIsbnListener(getIsbnListener);
        showBookList.setAdapter(noteBookAdapter);
        showBookList.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected int setContentViewId() {
        return R.layout.activity_mynote;
    }
    private void setCount(){
        List<Note> noteList = DataSupport.findAll(Note.class);
        noteCount.setText(""+noteList.size());
        noteBooks = DataSupport.findAll(NoteBook.class);
        bookCount.setText(""+noteBooks.size());
    }
}
