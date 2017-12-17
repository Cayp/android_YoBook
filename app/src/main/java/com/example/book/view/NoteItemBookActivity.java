package com.example.book.view;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.book.Adapter.ShowNoteAdapter;
import com.example.book.Base.BaseActivity;
import com.example.book.EntityClass.Note;
import com.example.book.MyView.ShowShareBookDetailDialog;
import com.example.book.R;
import com.example.book.Tools.MyApplication;
import com.example.book.view.AbstractView.ChangeToNoteDetailListener;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ljp on 2017/11/14.
 */

public class NoteItemBookActivity extends BaseActivity {
    @BindView(R.id.noteitemToolbar)
    Toolbar toolbar;
    @BindView(R.id.bookDetail)
    TextView bookDetail;
    @BindView(R.id.noteDetaillist)
    RecyclerView noteList;
    private String bookName;
    private String isbn;
    private List<Note> notes;
    private ChangeToNoteDetailListener changeToNoteDetailListener = new ChangeToNoteDetailListener() {
        @Override
        public void changeTo(View view, String isbn, int id) {
            Log.e("changeTo: ",""+id );
            Intent intent = new Intent(NoteItemBookActivity.this,NoteDetailActivity.class);
            intent.putExtra("isbn",isbn);
            intent.putExtra("id",""+id);
            startActivity(intent);
        }
    };
    @Override
    protected void initView(Bundle savedInstanceState) {
        initActionBar(toolbar,true);
        bookName = getIntent().getStringExtra("bookName");
        isbn = getIntent().getStringExtra("isbn");
        bookDetail.setText(bookName);
        bookDetail.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);   // 注册字体加下划线
        notes = getNote(isbn);
        ShowNoteAdapter showNoteAdapter = new ShowNoteAdapter();
        showNoteAdapter.setChangeToNoteDetailListener(changeToNoteDetailListener);
        showNoteAdapter.setNotes(notes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApplication.getContext());
        noteList.setAdapter(showNoteAdapter);
        noteList.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected int setContentViewId() {
        return R.layout.noteitembookdetail;
    }
    private List<Note> getNote(String isbn){
        return  DataSupport.where("isbn = ?",isbn).find(Note.class);
    }
    @OnClick(R.id.bookDetail)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bookDetail:
                ShowShareBookDetailDialog showShareBookDetailDialog = new ShowShareBookDetailDialog(this, R.style.Theme_AppCompat_Dialog_Alert,isbn);
                showShareBookDetailDialog.show();
                break;
        }
    }
}
