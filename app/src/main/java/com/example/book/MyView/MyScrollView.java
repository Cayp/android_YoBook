package com.example.book.MyView;

import android.content.Context;
import android.util.AttributeSet;

import android.view.MotionEvent;
import android.widget.ScrollView;

import com.example.book.view.AbstractView.OnScrollListener;

/**
 * Created by ljp on 2017/10/19.
 */

public class MyScrollView extends ScrollView {
    private OnScrollListener onScrollListener ;
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(onScrollListener != null){
            onScrollListener.onScoll(t);
        }
    }

    public OnScrollListener getOnScrollListener() {
        return onScrollListener;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }
}
