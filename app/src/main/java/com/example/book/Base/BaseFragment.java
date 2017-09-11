package com.example.book.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


/**
 * Created by ljp on 2017/8/1.
 */

public abstract class BaseFragment extends LazyLoadFragment{
    protected  View rootView;
    protected  abstract void initView(View view, Bundle savedInstanceState);
    protected  abstract int setLayoutId();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     if(rootView == null){
         rootView = inflater.inflate(setLayoutId(),container,false);
         ButterKnife.bind(this,rootView);
         initView(rootView,savedInstanceState);
     }
     return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup)rootView.getParent()).removeView(rootView);
    }
    protected void initActionBar(Toolbar toolbar){

    }
    protected void changeActivity(Class<?> cls){
        Intent intent = new Intent(getActivity(),cls);
        startActivity(intent);
    }
}
