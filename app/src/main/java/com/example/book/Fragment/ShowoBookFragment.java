package com.example.book.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.book.Adapter.TradeRecyclerAdapter;
import com.example.book.Base.BaseFragment;
import com.example.book.Base.LazyLoadFragment;
import com.example.book.EntityClass.SecondBookAllData;
import com.example.book.Presenter.GetBookPresenter;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.MyToast;
import com.example.book.view.AbstractView.PagingLoad;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewUtils;


import java.util.List;

import butterknife.BindView;

/**
 * Created by ljp on 2017/9/14.
 */

public class ShowoBookFragment extends LazyLoadFragment implements PagingLoad{
    @BindView(R.id.book_recyclerView)
    LRecyclerView lRecyclerView;
    TradeRecyclerAdapter tradeRecyclerAdapter;
    List<SecondBookAllData> dataList;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    GetBookPresenter getBookPresenter;
    public int page_no = 1 ;
    public static final int PAGE_SIZE = 5;

    @Override
    public void fetchData() {
        requestData();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
    tradeRecyclerAdapter = new TradeRecyclerAdapter();
    lRecyclerViewAdapter = new LRecyclerViewAdapter(tradeRecyclerAdapter);
    lRecyclerView.setAdapter(lRecyclerViewAdapter);
    lRecyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
    lRecyclerView.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
    lRecyclerView.setArrowImageView(R.drawable.turnhead);
    lRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
    lRecyclerView.setOnRefreshListener(new OnRefreshListener() {
        @Override
        public void onRefresh() {
            tradeRecyclerAdapter.clear();
            tradeRecyclerAdapter.notifyDataSetChanged();
            page_no = 1 ;
        }
    });
    lRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
        @Override
        public void onLoadMore() {
            requestData();
        }
    });

    }
    @Override
    protected int setLayoutId() {
        return R.layout.showbook_fragment;
    }

    @Override
    public void requestData() {
        getBookPresenter = new GetBookPresenter(this);
        getBookPresenter.requestData(page_no,PAGE_SIZE);

    }

    @Override
    public void failRequestData(int error) {
        switch (error){
            case Constant.ERROR_NOMOREUPDATE:
                lRecyclerView.setNoMore(true);
                MyToast.toast("wrong2");
                break;
            case 1:
                MyToast.toast("wrong");
        }
    }

    @Override
    public void succeedRequestData(List<SecondBookAllData> dataList) {
      if (tradeRecyclerAdapter.getItemCount()==0){
          tradeRecyclerAdapter.setDataList(dataList);

      }else {
          tradeRecyclerAdapter.addAll(dataList);
      }
      page_no++;
    }


}
