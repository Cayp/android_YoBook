package com.example.book.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.book.Base.BaseFragment;
import com.example.book.Base.LazyLoadFragment;
import com.example.book.Chat.activity.ChatActivity;
import com.example.book.R;
import com.github.jdsjlzx.recyclerview.LRecyclerView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ljp on 2017/9/8.
 */

public class RecommedFragment extends LazyLoadFragment {
    @BindView(R.id.chatWrapper_list)
    LRecyclerView chatWrapper_list;
    @BindView(R.id.chat_add)
    ImageView chat_add;

    public RecommedFragment() {
    }

    @Override
    public void fetchData() {

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.chat_layout;
    }


    @OnClick(R.id.chat_add)
    public  void  chatAddClick(View view){
        changeActivity(ChatActivity.class,2);
    }
}
