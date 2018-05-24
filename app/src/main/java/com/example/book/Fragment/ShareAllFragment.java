package com.example.book.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.book.Adapter.ShareAllRecyclerAdapter;
import com.example.book.Base.LazyLoadFragment;
import com.example.book.EntityClass.GetShareAllHelper;
import com.example.book.EntityClass.RegisterHelper;
import com.example.book.EntityClass.UserDataid_Icon;
import com.example.book.Presenter.GetShareAllPresenter;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.MyToast;
import com.example.book.Tools.NetworkUtils;
import com.example.book.Tools.UrlHelper;
import com.example.book.view.AbstractView.EnterToDetailListener;
import com.example.book.view.AbstractView.GetShare;
import com.example.book.view.AbstractView.LikeListener;
import com.example.book.view.AbstractView.WriteCommentListener;
import com.example.book.view.ShareDetail;
import com.example.book.view.WriteComment;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

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
        shareAllRecyclerAdapter.setEnterToDetailListener(new EnterToDetailListener() {
            @Override
            public void EnterToDetail(GetShareAllHelper getShareAllHelper) {
                changeToDetail(getShareAllHelper);
            }
        });
        shareAllRecyclerAdapter.setWriteCommentListener(new WriteCommentListener() {
            @Override
            public void enterToWriteComment(GetShareAllHelper getShareAllHelper) {
               changeToWriteComment(getShareAllHelper);
            }
        });
        shareAllRecyclerAdapter.setLikeListener(new LikeListener() {
            @Override
            public void like(int share_id) {
                    addLike(share_id);
            }
        });
        lRecyclerViewAdapter = new LRecyclerViewAdapter(shareAllRecyclerAdapter);
        shareAllPager.setAdapter(lRecyclerViewAdapter);
        shareAllPager.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
        shareAllPager.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
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
        setdata(getShareAllHelperList);
        lRecyclerViewAdapter.notifyDataSetChanged();
        shareAllPager.refreshComplete(PAGE_SIZE);
        page_no ++;
    }

    @Override
    public void setUserNameIcon(UserDataid_Icon userNameIcon) {
        /*userDataid_iconList.add(userNameIcon);
        if(userDataid_iconList.size() == getShareAllHelperList.size()){
            combineData();
            setdata(getShareAllHelperList);
            lRecyclerViewAdapter.notifyDataSetChanged();
            shareAllPager.refreshComplete(PAGE_SIZE);
            page_no ++;
        }*/
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
    public void changeToDetail(GetShareAllHelper getShareAllHelper){
        Intent intent = new Intent(MyApplication.getContext(), ShareDetail.class);
        intent.putExtra("share_item",getShareAllHelper);
        startActivity(intent);
    }
    public void changeToWriteComment(GetShareAllHelper getShareAllHelper){
        Intent intent = new Intent(MyApplication.getContext(), WriteComment.class);
        intent.putExtra("share_id",getShareAllHelper.getId());
        intent.putExtra("reply_id",getShareAllHelper.getUserId());
        startActivity(intent);
    }
    public void addLike(final int share_id){
        if(!NetworkUtils.isConnected()){
            MyToast.toast("没网了~");
        }else {
            try{
                OkHttpUtils.post()
                           .url(UrlHelper.ADDSTAR)
                           .addParams("share_id",""+share_id)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    RegisterHelper registerHelper = new Gson().fromJson(response,RegisterHelper.class);
                                    if(registerHelper.getCode()==20000){
                                        MyToast.toast("点赞成功");
                                    }else if(registerHelper.getCode()==40000){
                                           removeStar(share_id);
                                    }
                                }
                            });
            }catch (JsonIOException j){
                j.printStackTrace();
            }
        }
    }
    public void removeStar(int share_id){
        if(!NetworkUtils.isConnected()){
         MyToast.toast("没网了~");
        }else {
            try{
                OkHttpUtils.post()
                        .url(UrlHelper.REMOVESTAR)
                        .addParams("share_id",""+share_id)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                RegisterHelper registerHelper = new Gson().fromJson(response,RegisterHelper.class);
                                if(registerHelper.getCode()==20000){
                                    MyToast.toast("删除点赞成功");
                                }else if(registerHelper.getCode()==40000){

                                }
                            }
                        });
            }catch (JsonIOException j){
                j.printStackTrace();
            }
        }
    }
}
