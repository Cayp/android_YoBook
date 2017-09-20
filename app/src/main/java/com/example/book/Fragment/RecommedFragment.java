package com.example.book.Fragment;

import android.os.Bundle;
import android.view.View;

import com.example.book.Base.BaseFragment;
import com.example.book.Base.LazyLoadFragment;
import com.example.book.R;

/**
 * Created by ljp on 2017/9/8.
 */

public class RecommedFragment extends LazyLoadFragment {
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
        return R.layout.recommed_fragment;
    }
}
