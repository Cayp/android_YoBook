package com.example.book.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.book.EntityClass.GetShareStarHelper;
import com.example.book.R;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.UrlHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ljp on 2017/10/26.
 */

public class ShareStarAdapter extends RecyclerView.Adapter<ShareStarAdapter.ViewHodler> {
    private List<GetShareStarHelper.LIkeUserData> list = new ArrayList<>();

    static class ViewHodler extends RecyclerView.ViewHolder{
        private CircleImageView userIcon;
        private TextView userName;
        public ViewHodler(View itemView) {
            super(itemView);
        userIcon = (CircleImageView)itemView.findViewById(R.id.likeHeadIcon);
        userName = (TextView)itemView.findViewById(R.id.likeUserName);
        }
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.detailshowlike,null);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
       GetShareStarHelper.LIkeUserData onePisData = list.get(position);
       holder.userName.setText(onePisData.getUsername());
       Picasso.with(MyApplication.getContext()).load(UrlHelper.GETAVATAR + onePisData.getAvatar() )
                                               .error(R.mipmap.ic_launcher)
                                                .into(holder.userIcon);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setShareStarList(List<GetShareStarHelper.LIkeUserData> list){
        clearList();
        this.list.addAll(list);
    }
    public void clearList(){
        list.clear();
    }
}
