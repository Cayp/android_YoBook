package com.example.book.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.example.book.Base.BaseActivity;
import com.example.book.EntityClass.Note;
import com.example.book.R;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ljp on 2017/11/14.
 */

public class NoteDetailActivity extends BaseActivity {
    @BindView(R.id.noteDetailtoolbar)
    Toolbar toolbar;
    @BindView(R.id.noteTitle)
    TextView noteTitle;
    @BindView(R.id.noteContent)
    TextView noteContent;
    private String isbn;
    private int id;
    private Note note;
    @Override
    protected void initView(Bundle savedInstanceState) {
        initActionBar(toolbar,true);
         isbn = getIntent().getStringExtra("isbn");
         id  = Integer.parseInt(getIntent().getStringExtra("id"));
        List<Note> noteList = getNotes(isbn);
        Log.e("initView: ", ""+noteList.size());
       for (int i = 0;i<noteList.size();i++){
             if(noteList.get(i).getId()==id){
                 note = noteList.get(i);
                 break;
             }
       }
       noteTitle.setText(note.getTitle());
        noteContent.setText(note.getContent());

    }

    @Override
    protected int setContentViewId() {
        return R.layout.notedetail;
    }
    private List<Note> getNotes(String isbn){
        return DataSupport.where("isbn = ?",isbn).find(Note.class);
    }
}
