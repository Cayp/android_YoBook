package com.example.book.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.book.Adapter.ShareAllRecyclerAdapter;
import com.example.book.Base.LazyLoadFragment;
import com.example.book.EntityClass.GetShareAllHelper;
import com.example.book.EntityClass.UserDataid_Icon;
import com.example.book.Presenter.GetShareAllPresenter;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.MyToast;
import com.example.book.view.AbstractView.GetShare;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ljp on 2017/10/12.
 */

public class ShareAllFragment extends LazyLoadFragment implements GetShare{
    @BindView(R.id.shareAllPager)
    LRecyclerView shareAllPager;
    @BindView(R.id.loading_progressbar)
    ProgressBar loadingProgressbar;
    @BindView(R.id.loading_text)
    TextView loadingText;
    private ShareAllRecyclerAdapter shareAllRecyclerAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private List<UserDataid_Icon> userDataid_iconList = new ArrayList<>();
    private List<GetShareAllHelper> getShareAllHelperList = new ArrayList<>();
    private boolean isLoadMore = false;
    private int page_no = 1;
    private final int PAGE_SIZE = 4;
    private GetShareAllPresenter getShareAllPresenter;
    @Override
    public void fetchData() {
     getShare(page_no,PAGE_SIZE);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        getShare(page_no,PAGE_SIZE);
        shareAllRecyclerAdapter = new ShareAllRecyclerAdapter();
        lRecyclerViewAdapter = new LRecyclerViewAdapter(shareAllRecyclerAdapter);
        shareAllPager.setAdapter(lRecyclerViewAdapter);
        shareAllPager.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
        shareAllPager.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        shareAllPager.setArrowImageView(R.drawable.turnhead);
        shareAllPager.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        shareAllPager.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
             isLoadMore = false;
             clearList();
             page_no = 1;
             getShare(page_no,PAGE_SIZE);
            }
        });
        shareAllPager.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                clearList();
                isLoadMore = true;
                getShare(page_no,PAGE_SIZE);
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.shareallfragment;
    }

    @Override
    public void getShare(int page_no, int page_size) {
     getShareAllPresenter = new GetShareAllPresenter(this);
     getShareAllPresenter.getShare(page_no,page_size);
    }

    @Override
    public void failRequestData(int error) {
        switch (error) {
            case Constant.ERROR_NOMOREUPDATE:
                shareAllPager.setNoMore(true);
                break;
            case 1:
                MyToast.toast("发生未知问题，请重启应用");
                break;
            case Constant.ERROR_NO_INTERNET:
                MyToast.toast("无网络，请检查后下拉刷新");
                shareAllPager.refreshComplete(PAGE_SIZE);
                loadingText.setVisibility(View.GONE);
                loadingProgressbar.setVisibility(View.GONE);
                break;
            case Constant.ERROR_JSONGETWRONG:
                MyToast.toast("Json解析错误");
                break;
        }
    }

    @Override
    public void succeedRequestData(List<GetShareAllHelper> dataList) {
        getShareAllHelperList.addAll(dataList);
    }

    @Override
    public void setUserNameIcon(UserDataid_Icon userNameIcon) {
        userDataid_iconList.add(userNameIcon);
        if(userDataid_iconList.size() == getShareAllHelperList.size()){
            combineData();
            setdata(getShareAllHelperList);
            lRecyclerViewAdapter.notifyDataSetChanged();
            shareAllPager.refreshComplete(PAGE_SIZE);
            page_no ++;
        }
    }
    public void combineData(){
        for(int i = 0;i <getShareAllHelperList.size();i++){
            getShareAllHelperList.get(i).setUserName(userDataid_iconList.get(i).getUserName());
            getShareAllHelperList.get(i).setUserIcon(userDataid_iconList.get(i).getAvatar());
        }
    }
    public void setdata(List<GetShareAllHelper> list){
        if(isLoadMore){
            shareAllRecyclerAdapter.addList(list);
        }else {
            shareAllRecyclerAdapter.setMdataList(list);
            loadingText.setVisibility(View.GONE);
            loadingProgressbar.setVisibility(View.GONE);
        }

    }
    public void clearList(){
        if(getShareAllHelperList.size() != 0){
            getShareAllHelperList.clear();
        }
        if(userDataid_iconList.size() != 0){
            userDataid_iconList.clear();
        }
    }
}
