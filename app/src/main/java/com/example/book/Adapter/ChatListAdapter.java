package com.example.book.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.book.EntityClass.ChatBook;
import com.example.book.EntityClass.ChatObjects;
import com.example.book.R;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.UrlHelper;
import com.example.book.view.AbstractView.ChatItemListener;
import com.squareup.picasso.Picasso;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ljp on 2017/11/12.
 */

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {
    private ChatItemListener chatItemListener;
    private List<ChatObjects> chatObjectsList = new ArrayList<>();
    static class ViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView circleImageView;
        private TextView name;
        private TextView content;
        private RelativeLayout redCircle;
        private RelativeLayout chatItem;
        public ViewHolder(View itemView) {
            super(itemView);
            circleImageView = (CircleImageView)itemView.findViewById(R.id.wra_chatHeadIcon);
            name = (TextView)itemView.findViewById(R.id.wra_chatName);
            content = (TextView)itemView.findViewById(R.id.chat_item_content);
            redCircle = (RelativeLayout)itemView.findViewById(R.id.redCircle);
            chatItem = (RelativeLayout)itemView.findViewById(R.id.chatItem);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.chat_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ChatObjects chatObject = chatObjectsList.get(position);
        ChatBook chatBook = DataSupport.where("toUserId = ?", "" + chatObject.getToUserId()).findLast(ChatBook.class);
        holder.content.setText(chatBook.getContent());
        holder.name.setText(chatObject.getName());
        Picasso.with(MyApplication.getContext()).load(UrlHelper.GETAVATAR + chatObject.getAvatar() )
                                       .into(holder.circleImageView);
        if (chatItemListener != null) {
            holder.chatItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    chatItemListener.onItemClick(v,position,chatObjectsList);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return chatObjectsList.size();
    }
    public List<ChatObjects> getChatObjectsList (){
        return  chatObjectsList;
    }
    public void setChatObjectsList(List<ChatObjects> list){
        chatObjectsList.addAll(list);
    }
    public void addChatObjectsList(ChatObjects chatObject){
        chatObjectsList.add(chatObject);
        int indexx = chatObjectsList.size();
        notifyItemRangeInserted(indexx,1);
    }
    public void addAllChatObjectsList(List<ChatObjects> list){
        chatObjectsList.addAll(list);
        int index = chatObjectsList.size();
        notifyItemRangeInserted(index,list.size());

    }
    public void setOnClickListener(ChatItemListener clickListener){
          chatItemListener = clickListener;
    }
    public void clearlist(){
        chatObjectsList.clear();
    }
}
