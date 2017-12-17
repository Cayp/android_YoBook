package com.example.book.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.book.EntityClass.Note;
import com.example.book.EntityClass.NoteBook;
import com.example.book.R;
import com.example.book.Tools.MyApplication;
import com.example.book.view.AbstractView.GetIsbnListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljp on 2017/11/14.
 */

public class NotepublishAdapter extends RecyclerView.Adapter <NotepublishAdapter.ViewHolder>{
    private List<NoteBook> noteBookList = new ArrayList<>();
    private GetIsbnListener getIsbnListener;
    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView isbn;
        private TextView bookName;
        private RelativeLayout exitItem;
        public ViewHolder(View itemView) {
            super(itemView);
            isbn = (TextView)itemView.findViewById(R.id.isbn);
            bookName = (TextView)itemView.findViewById(R.id.bookname);
            exitItem = (RelativeLayout)itemView.findViewById(R.id.exitItem);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.exititem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
     NoteBook noteBook = noteBookList.get(position);
        holder.bookName.setText(noteBook.getName());
        holder.isbn.setText(noteBook.getIsbn());
        if(getIsbnListener!=null){
            holder.exitItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    Log.e( "onClick: ",""+noteBookList.size() );
                    getIsbnListener.chooseIsbn(v,position,noteBookList);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return noteBookList.size();
    }
    public void setNoteBookList(List<NoteBook> noteBookList){
        this.noteBookList.clear();
        this.noteBookList.addAll(noteBookList);
    }
    public void setGetIsbnListener(GetIsbnListener getIsbnListener){
        this.getIsbnListener = getIsbnListener;
    }
}
