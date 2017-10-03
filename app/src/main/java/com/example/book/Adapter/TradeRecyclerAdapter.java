package com.example.book.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.book.EntityClass.SecondBookAllData;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.UrlHelper;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljp on 2017/9/17.
 */

public class TradeRecyclerAdapter extends RecyclerView.Adapter<TradeRecyclerAdapter.ViewHolder> {
    private static final String TAG = "TradeRecyclerAdapter";
    private List<SecondBookAllData> mTradedatalist = new ArrayList<>();
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView head_icon;
        ImageView more_icon;
        ImageView book_picture;
        TextView user_name;
        TextView book_name;
        TextView description;
        RelativeLayout to_chat;
        RelativeLayout like;
        TextView price;
        TextView typeText;
        public ViewHolder(View itemView) {
            super(itemView);
            head_icon = (ImageView)itemView.findViewById(R.id.user_icon);
            more_icon = (ImageView)itemView.findViewById(R.id.more_icon);
            book_picture = (ImageView)itemView.findViewById(R.id.item_picture);
            user_name = (TextView) itemView.findViewById(R.id.user_name);
            book_name = (TextView)itemView.findViewById(R.id.bookname);
            description = (TextView)itemView.findViewById(R.id.description);
            to_chat = (RelativeLayout)itemView.findViewById(R.id.to_chat);
            like = (RelativeLayout)itemView.findViewById(R.id.like);
            typeText = (TextView)itemView.findViewById(R.id.typeText);
            price = (TextView)itemView.findViewById(R.id.price);
        }

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.findbok_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SecondBookAllData onePisData = mTradedatalist.get(position);
        holder.user_name.setText(onePisData.getUserName());
        holder.book_name.setText(onePisData.getBookName());
        holder.description.setText(onePisData.getDescription());
        Log.d(TAG, "fucj"+onePisData.getTypedId());
        if(onePisData.getTypedId()== Constant.CLASSBOOK){
            Log.d(TAG, "fuck2"+onePisData.getTypedId());
            holder.typeText.setText("教科书");
        }else{
            holder.typeText.setText("课外书");
        }
        Log.d(TAG, "price"+onePisData.getPrice());
        holder.price.setText(onePisData.getPrice());
        Picasso.with(MyApplication.getContext()).load(UrlHelper.GETSECONDBOOKCOVER+onePisData.getUserId()+"/"+onePisData.getBookCover())
                .fit()
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)                //不缓存在硬盘
                .into(holder.book_picture) ;
        Picasso.with(MyApplication.getContext()).load(UrlHelper.GETAVATAR+onePisData.getUserId()+"/"+onePisData.getAvatar())
                .error(R.mipmap.ic_launcher_round)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)                //不缓存在硬盘
                .into(holder.head_icon);
    }

    @Override
    public int getItemCount() {
        return mTradedatalist.size();
    }

    public List<SecondBookAllData> getDataList() {
        return mTradedatalist;
    }

    public void setDataList(List<SecondBookAllData> list) {
        clearList();
        this.mTradedatalist.addAll(list);
    }

    public void addAll(List<SecondBookAllData> list) {
        int lastIndex = this.mTradedatalist.size();
        if (this.mTradedatalist.addAll(list)) {
            notifyItemRangeInserted(lastIndex, list.size());
        }
    }

//    public void remove(int position) {
//        this.mTradedatalist.remove(position);
//        notifyItemRemoved(position);
//
//        if(position != (getDataList().size())){ // 如果移除的是最后一个，忽略
//            notifyItemRangeChanged(position,this.mTradedatalist.size()-position);
//        }
//    }

    private void clearList() {
       mTradedatalist.clear();
    }
}
