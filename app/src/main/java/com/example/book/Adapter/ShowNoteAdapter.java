package com.example.book.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.book.EntityClass.Note;
import com.example.book.R;
import com.example.book.Tools.MyApplication;
import com.example.book.view.AbstractView.ChangeToNoteDetailListener;
import com.google.zxing.oned.ITFReader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ljp on 2017/11/14.
 */

public class ShowNoteAdapter extends RecyclerView.Adapter <ShowNoteAdapter.ViewHolder>{
    private List<Note> notes;
    private ChangeToNoteDetailListener changeToNoteDetailListener;
    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView noteTitle;
        private TextView time;
        private CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            noteTitle = (TextView)itemView.findViewById(R.id.notetitle);
            time = (TextView)itemView.findViewById(R.id.time);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.shownoteitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
       final Note note  = notes.get(position);
        holder.noteTitle.setText(note.getTitle());
        String date =  explainTime(note.getTime());
        holder.time.setText(date);
        if(changeToNoteDetailListener!= null){
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    changeToNoteDetailListener.changeTo(v,notes.get(position).getIsbn(),notes.get(position).getId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
    public void setNotes(List<Note> notes){
        this.notes = notes;
    }
    public void setChangeToNoteDetailListener(ChangeToNoteDetailListener changeToNoteDetailListener){
        this.changeToNoteDetailListener = changeToNoteDetailListener;
    }
    private String explainTime(long time){
        Date thatTime = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        return simpleDateFormat.format(thatTime);
    }
}

