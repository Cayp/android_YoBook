package com.example.book.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.book.Chat.utils.AppUtil;
import com.example.book.EntityClass.GetShareAllHelper;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.UrlHelper;
import com.example.book.view.AbstractView.EnterToDetailListener;
import com.example.book.view.AbstractView.LikeListener;
import com.example.book.view.AbstractView.WriteCommentListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import rx.internal.operators.OperatorMapNotification;

/**
 * Created by ljp on 2017/10/14.
 */

public class ShareAllRecyclerAdapter extends RecyclerView.Adapter<ShareAllRecyclerAdapter.ViewHolder> {
    private List<GetShareAllHelper> mdataList = new ArrayList<>();
    private EnterToDetailListener enterToDetailListener;
    private WriteCommentListener writeCommentListener;
    private LikeListener likeListener;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView headIcon;
        TextView userName;
        TextView time ;
        ImageView downback;
        TextView bookName;
        TextView shareContent;
        TextView touchGetMore;
        ImageView shareCover;
        TextView commentSum;
        TextView likeSum;
        LinearLayout enterToDetail;
        RelativeLayout commentWrapper;
        RelativeLayout like;
        public ViewHolder(View itemView) {
            super(itemView);
            headIcon = (CircleImageView)itemView.findViewById(R.id.userheadicon);
            userName = (TextView)itemView.findViewById(R.id.user_name);
            time = (TextView)itemView.findViewById(R.id.user_time);
            downback = (ImageView)itemView.findViewById(R.id.downback);
            bookName = (TextView)itemView.findViewById(R.id.bookname);
            shareContent = (TextView)itemView.findViewById(R.id.shareContent);
            touchGetMore = (TextView)itemView.findViewById(R.id.touchGetMore);
            shareCover = (ImageView)itemView.findViewById(R.id.sharecover);
            commentSum = (TextView)itemView.findViewById(R.id.commenSum);
            likeSum = (TextView)itemView.findViewById(R.id.likeSum);
            enterToDetail = (LinearLayout)itemView.findViewById(R.id.enterToDetail);
            commentWrapper = (RelativeLayout)itemView.findViewById(R.id.commentwrapper);
            like = (RelativeLayout)itemView.findViewById(R.id.like);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shareall_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
     GetShareAllHelper onePisData = mdataList.get(position);
     holder.userName.setText(onePisData.getUserName());
     holder.bookName.setText("《"+onePisData.getBookName()+"》");
     long timeInterval = AppUtil.getNowTime() - onePisData.getTime();
      if(timeInterval < Constant.TWENTYTHREEHOUR){
         holder.time.setText(""+(int)(timeInterval/Constant.ONEHOUR)+"小时前");
        } else if(timeInterval < Constant.ONEHOUR){
          holder.time.setText(""+(int)(timeInterval/Constant.ONEHOUR)+"小时前");
      }
      else {
         String date =  explainTime(onePisData.getTime());
         holder.time.setText(date);
      }
     String content = onePisData.getContent();
     if(content.length()>92){
         holder.shareContent.setText(content);
         holder.touchGetMore.setVisibility(View.VISIBLE);
     }else {
         holder.shareContent.setText(content);
     }
     holder.commentSum.setText(""+onePisData.getCommentNum()+"");
     holder.likeSum.setText(""+onePisData.getStarNum()+"");
     if(onePisData.getBookCover() != null){
         holder.shareCover.setVisibility(View.VISIBLE);
         Picasso.with(MyApplication.getContext())
                 .load(UrlHelper.GETSHARECOVER + onePisData.getUserId()+"/"+onePisData.getId()+"/"+onePisData.getBookCover())
                 .fit()
                 .into(holder.shareCover);
     }
     Picasso.with(MyApplication.getContext())
             .load(UrlHelper.GETAVATAR + onePisData.getUserId() + "/" + onePisData.getUserIcon())
             .into(holder.headIcon);
     if(enterToDetailListener != null){
         holder.enterToDetail.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 int position = holder.getLayoutPosition();
                 enterToDetailListener.EnterToDetail(mdataList.get(position-1));
             }
         });
     }
     if (writeCommentListener != null){
         holder.commentWrapper.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 int position = holder.getLayoutPosition();
                 writeCommentListener.enterToWriteComment(mdataList.get(position-1));
             }
         });
     }
     if(likeListener!=null){
         holder.like.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 int position = holder.getLayoutPosition();
                 likeListener.like(mdataList.get(position-1).getId());
             }
         });
     }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }
    public void setMdataList(List<GetShareAllHelper> mdataList){
        clearList();
        this.mdataList.addAll(mdataList);
    }
    public void addList(List<GetShareAllHelper> mdataList){
        int lastIndex = this.mdataList.size();
        this.mdataList.addAll(mdataList);
        notifyItemRangeInserted(lastIndex,mdataList.size());
    }
    private void clearList(){
        mdataList.clear();
    }
    private String explainTime(long time){
        Date thatTime = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        return simpleDateFormat.format(thatTime);
    }
    public void setEnterToDetailListener(EnterToDetailListener enterToDetailListener){
        this.enterToDetailListener = enterToDetailListener;
    }
    public void setWriteCommentListener(WriteCommentListener writeCommentListener){
        this.writeCommentListener = writeCommentListener;
    }
    public void setLikeListener(LikeListener likeListener){
        this.likeListener = likeListener;
    }
}
