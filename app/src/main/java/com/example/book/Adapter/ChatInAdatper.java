package com.example.book.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.book.Chat.entity.Message;
import com.example.book.R;
import com.example.book.Tools.Constant;

import java.util.List;

/**
 * Created by ljp on 2017/10/8.
 */

public class ChatInAdatper extends RecyclerView.Adapter {
    private List<Message> messages ;
    private int from_me_id;
    private int to_id;
    public ChatInAdatper(List<Message> messages,int from_id ,int to_id) {
        this.messages = messages;
        this.from_me_id = from_id;
        this.to_id = to_id;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        if(message.getToId()==to_id&&message.getFromId()==from_me_id){
            return Constant.CHAT_RIGHT;
        }else if (message.getToId()==from_me_id&&message.getFromId()==to_id){
            return Constant.CHAT_LEFT;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
    public void addData(){

    }
    class SendTxtViewHolder extends RecyclerView.ViewHolder{
        private TextView content;
        private TextView time;

        public SendTxtViewHolder(View itemView) {
            super(itemView);
            content = (TextView)itemView.findViewById(R.id.tv_item_send_txt);
            time = (TextView)itemView.findViewById(R.id.tv_item_from_txt);
        }
    }
    class FromTxtVIewHolder extends RecyclerView.ViewHolder {
        private TextView content;
        private TextView time;
        public FromTxtVIewHolder(View view) {
            super(view);
           content = (TextView) view.findViewById(R.id.tv_item_from_txt);
            time = (TextView)view.findViewById(R.id.tv_from_msg_date);
        }
    }

}
