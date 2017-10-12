package com.example.book.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.book.Adapter.TradeRecyclerAdapter;
import com.example.book.Base.LazyLoadFragment;
import com.example.book.EntityClass.SecondBookAllData;
import com.example.book.EntityClass.UserDataid_Icon;
import com.example.book.Presenter.GetBookPresenter;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.MyToast;
import com.example.book.Tools.Mylog;
import com.example.book.view.AbstractView.PagingLoad;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ljp on 2017/9/14.
 */

public class ShowoBookFragment extends LazyLoadFragment implements PagingLoad {
    @BindView(R.id.loading_progressbar)
    ProgressBar loading_progressbar;
    @BindView(R.id.book_recyclerView)
    LRecyclerView lRecyclerView;
    @BindView(R.id.loading_text)
    TextView loadtext;
    TradeRecyclerAdapter tradeRecyclerAdapter;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    GetBookPresenter getBookPresenter;

    private List<SecondBookAllData> mdataList = new ArrayList<>();
    private boolean isLoadMore = false;                    //添加判断是否是向下加载更多
    private List<UserDataid_Icon> mList = new ArrayList<>();
    private int page_no = 1;
    private final int PAGE_SIZE = 4;
    private static final String TAG = "ShowoBookFragment";
    @Override
    public void fetchData() {
        requestData();

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        requestData();
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
                isLoadMore = false;
                clearList();
                page_no = 1;
                requestData();
            }
        });
        lRecyclerView.setLoadMoreEnabled(true);
        lRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                clearList();
                isLoadMore = true;
                Log.d(TAG, "onLoadMore"+page_no);
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
        getBookPresenter.requestData(page_no,PAGE_SIZE,0);
    }

    @Override
    public void failRequestData(int error) {
        switch (error) {
            case Constant.ERROR_NOMOREUPDATE:
                lRecyclerView.setNoMore(true);
                break;
            case 1:
                MyToast.toast("发生未知问题，请重启应用");
                break;
            case Constant.ERROR_NO_INTERNET:
                MyToast.toast("无网络，请检查后下拉刷新");
                lRecyclerView.refreshComplete(PAGE_SIZE);
                lRecyclerView.setOnNetWorkErrorListener(new OnNetWorkErrorListener() {
                    @Override
                    public void reload() {
                        requestData();
                    }
                });
                break;
        }
    }

    @Override
    public void succeedRequestData(List<SecondBookAllData> dataList) {
        mdataList.addAll(dataList);
    }
    @Override
    public void setUserNameIcon(UserDataid_Icon userNameIcon) {
         mList.add(userNameIcon);
        if (mList.size() == mdataList.size()) {
            Mylog.d(TAG, "yCAYP");
            combineData();
            setdata(mdataList);
            Mylog.d(TAG, "Tsecond");
            lRecyclerViewAdapter.notifyDataSetChanged();
            lRecyclerView.refreshComplete(PAGE_SIZE);
            page_no++;
        }
    }
    public void combineData(){
        for(int i=0 ;i < mdataList.size();i++){
            mdataList.get(i).setUserName(mList.get(i).getUserName());
            mdataList.get(i).setAvatar(mList.get(i).getAvatar());
        }
    }
    public void clearList(){
        if(mdataList.size()!=0){
        mdataList.clear();
        }
        if(mList.size()!=0){
        mList.clear();
        }
    }
    public void setdata(List<SecondBookAllData> list){
        Log.d(TAG, "setdata"+isLoadMore);
        if(isLoadMore){
           tradeRecyclerAdapter.addAll(list);
       }else {
            tradeRecyclerAdapter.setDataList(list);
            loadtext.setVisibility(View.GONE);
            loading_progressbar.setVisibility(View.GONE);
       }
    }
}
