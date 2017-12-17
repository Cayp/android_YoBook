package com.example.book.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.book.EntityClass.Note;
import com.example.book.EntityClass.NoteBook;
import com.example.book.R;
import com.example.book.Tools.MyApplication;
import com.example.book.view.AbstractView.GetIsbnListener;
import com.squareup.picasso.Picasso;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by ljp on 2017/11/14.
 */

public class MyNoteBookAdapter extends RecyclerView.Adapter<MyNoteBookAdapter.ViewHolder> {
    private List<NoteBook> noteBooks;
    private GetIsbnListener getIsbnListener;
    static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView booCover;
        private TextView bookName;
        private TextView noteCount;
        private CardView toDetail;
        public ViewHolder(View itemView) {
            super(itemView);
            booCover = (ImageView)itemView.findViewById(R.id.bookCover);
            bookName = (TextView)itemView.findViewById(R.id.bookname);
            noteCount = (TextView)itemView.findViewById(R.id.boknameCount);
            toDetail = (CardView)itemView.findViewById(R.id.toDetail);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.notebookitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        NoteBook noteBook = noteBooks.get(position);
        List<Note> noteList = DataSupport.where("isbn = ?",noteBook.getIsbn()).find(Note.class);
        holder.noteCount.setText(""+noteList.size());
        holder.bookName.setText(noteBook.getName());
        Picasso.with(MyApplication.getContext()).load(noteBook.getCoverUrl()).into(holder.booCover);
        if(getIsbnListener!=null){
            holder.toDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    getIsbnListener.chooseIsbn(v,position,noteBooks);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return noteBooks.size();
    }
    public void setNoteBooks(List<NoteBook> list){
        noteBooks = list;
    }
    public void setGetIsbnListener(GetIsbnListener getIsbnListener){
        this.getIsbnListener = getIsbnListener;
    }
}
