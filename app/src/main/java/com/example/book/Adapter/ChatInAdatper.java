package com.example.book.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.book.Chat.entity.Message;
import com.example.book.EntityClass.ChatItem;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljp on 2017/10/8.
 */

public class ChatInAdatper extends RecyclerView.Adapter<ChatInAdatper.ViewHolder> {
    private  List<ChatItem> messageList = new ArrayList<>();
    private int from_me_id;
    private int to_id;
      static  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView content;
        private ViewHolder(View view) {
            super(view);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
          if(viewType == Constant.CHAT_RIGHT){
                view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.chat_right,null);
                return new RightTxtVIewHolder(view);
          }else if (viewType == Constant.CHAT_LEFT){
                 view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.chat_left,null);
                return new LeftTxtViewHolder(view);
        }
        view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.chat_right,null);
        return new RightTxtVIewHolder(view);
    }


    @Override
    public int getItemViewType(int position) {
        ChatItem message =messageList.get(position);
        if(message.getDirection()==Constant.CHAT_RIGHT){
            return Constant.CHAT_RIGHT;
        }else if (message.getDirection()==Constant.CHAT_LEFT){
            return Constant.CHAT_LEFT;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    private class LeftTxtViewHolder extends ViewHolder{
        private TextView content;
        private LeftTxtViewHolder(View itemView) {
            super(itemView);
            content = (TextView)itemView.findViewById(R.id.leftText);
        }
    }
    private class RightTxtVIewHolder extends ViewHolder {
        private TextView content;
        private RightTxtVIewHolder(View view) {
            super(view);
           content = (TextView) view.findViewById(R.id.chatRight);
        }
    }
    public void setChatList(List<ChatItem> messageList){
        this.messageList = messageList;
    }
    public void addOne(ChatItem message){
        int lastIndex = messageList.size();
        messageList.add(message);
        notifyItemRangeInserted(lastIndex,1);
    }
    public void addList(List<ChatItem> messageList){
        int lastIndex = this.messageList.size();
        this.messageList.addAll(messageList);
        notifyItemRangeInserted(lastIndex,messageList.size());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChatItem chatItem = messageList.get(position);
        holder.content.setText(chatItem.getContent());
    }
}
