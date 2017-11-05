package com.example.book.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.book.Chat.utils.AppUtil;
import com.example.book.EntityClass.GetCommentHelper;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.UrlHelper;
import com.example.book.view.AbstractView.GetComment;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ljp on 2017/10/23.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<GetCommentHelper.CommentItem> commentItemList = new ArrayList<>();
    static class ViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView headIcon;
        private TextView userName;
        private TextView content;
        private TextView time;
        public ViewHolder(View itemView) {
            super(itemView);
            headIcon = (CircleImageView)itemView.findViewById(R.id.commentHeadicon);
            userName = (TextView)itemView.findViewById(R.id.commentUserName);
            content  = (TextView)itemView.findViewById(R.id.commentContent);
            time   =  (TextView)itemView.findViewById(R.id.commentTime);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.commentitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      GetCommentHelper.CommentItem commentItem = commentItemList.get(position);
      holder.userName.setText(commentItem.getReplyUsername());
      holder.content.setText(commentItem.getContent());
      long timeInterval = AppUtil.getNowTime() - commentItem.getTime();
        if(timeInterval < Constant.TWENTYTHREEHOUR&& timeInterval >= Constant.ONEHOUR) {
            holder.time.setText("" + (int) (timeInterval / Constant.ONEHOUR) + "小时前");
        }else if(timeInterval < Constant.ONEHOUR&&timeInterval>=Constant.ONEMI){
            holder.time.setText(""+(int)(timeInterval/Constant.ONEMI)+"分钟前");
        }else if(timeInterval < Constant.ONEMI&&timeInterval>=Constant.ONESEC) {
            holder.time.setText(""+(int)(timeInterval/Constant.ONESEC)+"秒前");
        }
        else {
            String date =  explainTime(commentItem.getTime());
            holder.time.setText(date);
        }
        Picasso.with(MyApplication.getContext()).load(UrlHelper.GETAVATAR  + commentItem.getAvatar())
                  .error(R.mipmap.ic_launcher)
                  .into(holder.headIcon);
    }

    @Override
    public int getItemCount() {
        return commentItemList.size();
    }


    public void setCommentList(List<GetCommentHelper.CommentItem> datalist){
        clearList();
        commentItemList.addAll(datalist);
    }
    public void addCommentList(List<GetCommentHelper.CommentItem> datalist){
        int lastIndex = commentItemList.size();
        commentItemList.addAll(datalist);
        notifyItemRangeInserted(lastIndex,datalist.size());
    }
    private void clearList(){
        commentItemList.clear();
    }
    private String explainTime(long time){
        Date thatTime = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        return simpleDateFormat.format(thatTime);
    }
}
