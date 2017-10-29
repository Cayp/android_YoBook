package com.example.book.MyView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.book.R;
import com.example.book.view.AbstractView.LoadMoreListener;

/**
 * Created by ljp on 2017/10/22.
 */

public class CommentListView extends ListView implements AbsListView.OnScrollListener{
    private int lasVisibleItem;
    private int totalItemCount;
    private boolean isLoading = false;
    private LoadMoreListener loadMoreListener;
    private View footer;
    public CommentListView(Context context) {
        super(context);
    }

    public CommentListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommentListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void initView(Context context){
        footer = LayoutInflater.from(context).inflate(R.layout.commentfooter,null);
        this.addFooterView(footer);
        footer.findViewById(R.id.load_layout).setVisibility(GONE);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(lasVisibleItem == totalItemCount && scrollState == SCROLL_STATE_IDLE){
            if(!isLoading){
                isLoading = true;
                footer.findViewById(R.id.load_layout).setVisibility(VISIBLE);
                loadMoreListener.LoadMore();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
       this.lasVisibleItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }
}
