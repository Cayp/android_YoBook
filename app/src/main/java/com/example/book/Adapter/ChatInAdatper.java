package com.example.book.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.book.Chat.entity.Message;
import com.example.book.EntityClass.ChatBook;
import com.example.book.EntityClass.ChatItem;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljp on 2017/10/8.
 */

public class ChatInAdatper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  List<ChatBook> messageList = new ArrayList<>();
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
          if(viewType == Constant.CHAT_RIGHT){
                view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.chat_right,parent,false);
                return new RightTxtVIewHolder(view);
          }else{
                 view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.chat_left,parent,false);
                return new LeftTxtViewHolder(view);
        }
    }


    @Override
    public int getItemViewType(int position) {
        ChatBook message =messageList.get(position);
        if(message.getDirection()==Constant.CHAT_RIGHT){
            return Constant.CHAT_RIGHT;
        }else {
            return Constant.CHAT_LEFT;
        }

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    private class LeftTxtViewHolder extends RecyclerView.ViewHolder{
        private TextView content;
        private LeftTxtViewHolder(View itemView) {
            super(itemView);
            content = (TextView)itemView.findViewById(R.id.leftText);
        }
    }
    private class RightTxtVIewHolder extends RecyclerView.ViewHolder {
        private TextView content;
        private RightTxtVIewHolder(View view) {
            super(view);
           content = (TextView) view.findViewById(R.id.chatRight);
        }
    }
    public void setChatList(List<ChatBook> messageList){
        this.messageList.addAll(messageList);
    }
    public void addOne(ChatBook message){
        int lastIndex = messageList.size();
        messageList.add(message);
        Log.e("chatInad","?");
        notifyItemRangeInserted(lastIndex,1);
    }
    public void addList(List<ChatBook> messageList){
        int lastIndex = this.messageList.size();
        this.messageList.addAll(messageList);
        notifyItemRangeInserted(lastIndex,messageList.size());

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("chatInAdapter",""+messageList.get(position).getContent() );
        ChatBook chatItem = messageList.get(position);
        if(holder instanceof LeftTxtViewHolder){
           ((LeftTxtViewHolder) holder).content.setText(chatItem.getContent());
        }else {
            ((RightTxtVIewHolder)holder).content.setText(chatItem.getContent());
        }
    }
}
