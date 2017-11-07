package com.example.book.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.book.Adapter.CommentAdapter;
import com.example.book.Adapter.ShareStarAdapter;
import com.example.book.Base.BaseActivity;
import com.example.book.Chat.utils.AppUtil;
import com.example.book.EntityClass.GetCommentHelper;
import com.example.book.EntityClass.GetShareAllHelper;
import com.example.book.EntityClass.GetShareStarHelper;
import com.example.book.EntityClass.RegisterHelper;
import com.example.book.EntityClass.UserDataid_Icon;
import com.example.book.MyView.MyScrollView;
import com.example.book.MyView.ShowShareBookDetailDialog;
import com.example.book.Presenter.GetCommentPresenter;
import com.example.book.Presenter.GetShareStarPresenter;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.MyToast;
import com.example.book.Tools.NetworkUtils;
import com.example.book.Tools.UrlHelper;
import com.example.book.view.AbstractView.GetComment;
import com.example.book.view.AbstractView.GetShareStar;
import com.example.book.view.AbstractView.OnScrollListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

/**
 * Created by ljp on 2017/10/18.
 */

public class ShareDetail extends BaseActivity implements OnScrollListener,TabLayout.OnTabSelectedListener,GetComment,GetShareStar{
    @BindView(R.id.shareDetailToolbar)
    Toolbar  shareDetailToolbar;
    @BindView(R.id.search01)
    LinearLayout search01 ;
    @BindView(R.id.search02)
    LinearLayout search02;
    @BindView(R.id.view2)
    View  view2;
    @BindView(R.id.topPart)
    LinearLayout topPart;
    @BindView(R.id.myscrollView)
    MyScrollView myScrollView;
    @BindView(R.id.sharetablayout)
    TabLayout sharetablayout;
    @BindView(R.id.share1headicon)
    CircleImageView share1headicon;
    @BindView(R.id.share1UserName)
    TextView share1UserName;
    @BindView(R.id.userheadicon)
    CircleImageView userheadicon;
    @BindView(R.id.user_time)
    TextView user_time;
    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.downback)
    ImageView downback;
    @BindView(R.id.bookname)
    TextView bookname ;
    @BindView(R.id.shareContent)
    TextView shareContent;
    @BindView(R.id.sharecover)
    ImageView shareCover;
    @BindView(R.id.shareCommentView)
    LRecyclerView shareCommentView;
    @BindView(R.id.showNoComment)
    RelativeLayout showNoComment;
    @BindView(R.id.loadingPart)
    RelativeLayout loadingPart;
    @BindView(R.id.showNoset)
    TextView showNoset;
    @BindView(R.id.commentwrapper)
    RelativeLayout commentwrapper;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.like)
    RelativeLayout like;
    @BindView(R.id.sharecover1)
    ImageView shareCover1;
    @BindView(R.id.sharecoverWrapper)
    RelativeLayout sharecoverWrapper;
    private static final String TAG = "ShareDetail";
    private GetShareAllHelper getShareAllHelper;
    private CommentAdapter commentAdapter;
    private ShareStarAdapter shareStarAdapter;
    private List<GetCommentHelper.CommentItem> commentItemList = new ArrayList<>();
    private List<UserDataid_Icon> userDataList = new ArrayList<>();
    private List<GetShareStarHelper.LIkeUserData> likeList = new ArrayList<>();
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter2;
    private boolean ishaveloaded =false;
    private boolean isShareStarLoaded = false;
    private int  topPartH;
    private int  scrollViewH;
    private int page_no = 1;
    private final int PAGE_SIZE = 100 ;
    private int thisShareId ;
    @Override
    protected void initView(Bundle savedInstanceState) {
     initActionBar(shareDetailToolbar,true);
     myScrollView.setOnScrollListener(this);
     iniTayout();    //初始化tablayout
     sharetablayout.addOnTabSelectedListener(this);
     getShareAllHelper = (GetShareAllHelper) getIntent().getSerializableExtra("share_item");
     thisShareId = getShareAllHelper.getId();
     initUserData(getShareAllHelper);
     commentAdapter = new CommentAdapter();
     shareStarAdapter = new ShareStarAdapter();
     lRecyclerViewAdapter =new LRecyclerViewAdapter(commentAdapter);
     lRecyclerViewAdapter2 = new LRecyclerViewAdapter(shareStarAdapter);
     shareCommentView.setAdapter(lRecyclerViewAdapter);
     shareCommentView.setPullRefreshEnabled(false);
     shareCommentView.setNestedScrollingEnabled(false);
     shareCommentView.setLayoutManager(new LinearLayoutManager(this));
     swipeRefresh.setColorSchemeColors(Color.parseColor("#9ec7fe"));
     swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
         @Override
         public void onRefresh() {
             if (sharetablayout.getSelectedTabPosition() == 0) {
                 getComment(thisShareId, page_no, PAGE_SIZE);
                 Log.d(TAG, "this 0 ");
             } else {
                 Log.d(TAG, "this 1 ");
                 getShareStar(thisShareId);

             }
         }
     });
     getComment(thisShareId,page_no,PAGE_SIZE);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            topPartH = topPart.getBottom();
            scrollViewH = myScrollView.getBottom();
        }
    }

    @Override
    protected int setContentViewId() {
        return R.layout.sharedetailactivity;
    }





    @Override
    public void onScoll(int scrollY) {
        if(scrollY >= topPartH ){
            if(view2.getParent()!=search01){
                search02.removeView(view2);
                search01.addView(view2);
                search01.setVisibility(View.VISIBLE);
            }
         } else {
            if(view2.getParent()!=search02){
                search01.removeView(view2);
                search02.addView(view2);
                search01.setVisibility(View.GONE);

            }
        }

    }
    private void iniTayout(){
        sharetablayout.addTab(sharetablayout.newTab().setText("评论 0"));
        sharetablayout.addTab(sharetablayout.newTab().setText("赞 0"));

    }
    private void initUserData(GetShareAllHelper getShareAllHelper){
         share1UserName.setText(getShareAllHelper.getUserName());
         user_name.setText(getShareAllHelper.getUserName());
         bookname.setText("《"+getShareAllHelper.getBookName()+"》");
         shareContent.setText(getShareAllHelper.getContent());
        if(getShareAllHelper.getBookcovers() != null&&!getShareAllHelper.getBookcovers().equals("")) {
            sharecoverWrapper.setVisibility(View.VISIBLE);
            String[] bookCover = getShareAllHelper.getBookcovers().split(",");
            switch (bookCover.length) {
                case 1:
                    Picasso.with(MyApplication.getContext())
                            .load(UrlHelper.GETSHARECOVER + getShareAllHelper.getUserId() + "/" + getShareAllHelper.getId() + "/" + bookCover[0])
                            .fit()
                            .into(shareCover);
                    break;
                case 2:
                    Picasso.with(MyApplication.getContext())
                            .load(UrlHelper.GETSHARECOVER + getShareAllHelper.getUserId() + "/" + getShareAllHelper.getId() + "/" + bookCover[0])
                            .fit()
                            .into(shareCover);
                    Picasso.with(MyApplication.getContext())
                            .load(UrlHelper.GETSHARECOVER + getShareAllHelper.getUserId() + "/" + getShareAllHelper.getId() + "/" + bookCover[1])
                            .fit()
                            .into(shareCover1);
                    break;
            }
        }
         Picasso.with(this)
                 .load(UrlHelper.GETAVATAR  + getShareAllHelper.getUserIcon())
                 .into(userheadicon);
         Picasso.with(this)
                .load(UrlHelper.GETAVATAR + getShareAllHelper.getUserIcon())
                .into(share1headicon);
        long timeInterval = AppUtil.getNowTime() - getShareAllHelper.getTime();
        if(timeInterval < Constant.TWENTYTHREEHOUR &&timeInterval >= Constant.ONEHOUR){
            user_time.setText(""+(int)(timeInterval/Constant.ONEHOUR)+"小时前");
        }else if(timeInterval < Constant.ONEHOUR&&timeInterval>=Constant.ONEMI){
           user_time.setText(""+(int)(timeInterval/Constant.ONEMI)+"分钟前");
        }else if(timeInterval < Constant.ONEMI&&timeInterval>=Constant.ONESEC) {
           user_time.setText(""+(int)(timeInterval/Constant.ONESEC)+"秒前");
        }else {
            String date =  explainTime(getShareAllHelper.getTime());
            user_time.setText(date);
        }
        sharetablayout.getTabAt(1).setText("赞 "+getShareAllHelper.getStarNum());
        sharetablayout.getTabAt(0).setText("评论 "+getShareAllHelper.getCommentNum());
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if(tab == sharetablayout.getTabAt(1)){
         showNoset.setText("还没有人赞");
            shareStarAdapter.setShareStarList(likeList);
            lRecyclerViewAdapter2.notifyDataSetChanged();
            shareCommentView.setAdapter(lRecyclerViewAdapter2);
         if(!isShareStarLoaded) {
             loadingPart.setVisibility(View.VISIBLE);
             getShareStar(thisShareId);
         }else {
             if(shareStarAdapter.getItemCount()!=0){
               if(showNoComment.getVisibility()==View.VISIBLE){
                   showNoComment.setVisibility(View.GONE);
                   shareCommentView.setVisibility(View.VISIBLE);
               }
             }else {
               if(showNoComment.getVisibility()==View.GONE){
                     showNoComment.setVisibility(View.VISIBLE);
               }
             }
         }

        }else if(tab == sharetablayout.getTabAt(0)){
         showNoset.setText("还没有人评论");
         commentAdapter.setCommentList(commentItemList);
         lRecyclerViewAdapter.notifyDataSetChanged();
         shareCommentView.setAdapter(lRecyclerViewAdapter);
         if(commentAdapter.getItemCount()!=0){
             if(showNoComment.getVisibility()==View.VISIBLE){
                 showNoComment.setVisibility(View.GONE);
                 shareCommentView.setVisibility(View.VISIBLE);
             }
         }else {
             if (showNoComment.getVisibility()==View.GONE){
                 showNoComment.setVisibility(View.VISIBLE);
                 shareCommentView.setVisibility(View.GONE);
             }
         }
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
      if(tab == sharetablayout.getTabAt(1)){
         getShareStar(thisShareId);
      }else if(tab == sharetablayout.getTabAt(0)){
         getComment(thisShareId,page_no,PAGE_SIZE);
      }
    }
    private String explainTime(long time){
        Date thatTime = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        return simpleDateFormat.format(thatTime);
    }

    @Override
    public void getComment(int share_id, int page_no, int page_size) {
        clearList();
        loadingPart.setVisibility(View.VISIBLE);
        GetCommentPresenter getCommentPresenter = new GetCommentPresenter(this);
        getCommentPresenter.getComment(share_id,page_no,page_size);
    }

    @Override
    public void getCommentSucceed(List<GetCommentHelper.CommentItem> list) {
        commentItemList.addAll(list);
    }
    @Override
    public void getUserData(UserDataid_Icon userDataid_icon) {
        userDataList.add(userDataid_icon);
        if(userDataList.size()==commentItemList.size()){
            combineData();
            swipeRefresh.setRefreshing(false);
            ishaveloaded = true;
            showNoComment.setVisibility(View.GONE);
            shareCommentView.setVisibility(View.VISIBLE);
            commentAdapter.setCommentList(commentItemList);
            sharetablayout.getTabAt(0).setText("评论 "+commentAdapter.getItemCount());
            shareCommentView.refreshComplete(PAGE_SIZE);
            swipeRefresh.setRefreshing(false);
            loadingPart.setVisibility(View.GONE);
        }
    }
   public  void combineData(){
       for(int i = 0 ;i<commentItemList.size();i++){
           commentItemList.get(i).setAvatar(userDataList.get(i).getAvatar());
           commentItemList.get(i).setReplyUsername(userDataList.get(i).getUserName());
       }
   }
   private void clearList(){
       if(commentItemList.size()!=0){
           commentItemList.clear();
       }
       if(userDataList.size()!=0){
           userDataList.clear();
       }
   }
   private void clearLikeList(){
       if(likeList.size()!=0){
           likeList.clear();
       }
   }
    @Override
    public void getCommentFailure(int error) {
      switch (error){
          case Constant.ERROR_JSONGETWRONG:
              MyToast.toast("Json数据解析错误");
              break;
          case Constant.ERROR_NO_INTERNET:
              MyToast.toast("网络未连接,请检查后重启");
              break;
          case 2:
              swipeRefresh.setRefreshing(false);
              loadingPart.setVisibility(View.GONE);
              if(ishaveloaded){
                  showNoComment.setVisibility(View.GONE);
              }
              else {
                  showNoComment.setVisibility(View.VISIBLE);
              }
              shareCommentView.refreshComplete(PAGE_SIZE);

      }
    }
    public void changeToWriteComment(GetShareAllHelper getShareAllHelper){
        Intent intent = new Intent(MyApplication.getContext(), WriteComment.class);
        intent.putExtra("share_id",getShareAllHelper.getId());
        intent.putExtra("reply_id",getShareAllHelper.getUserId());
        startActivity(intent);
    }
    @OnClick({R.id.commentwrapper,R.id.like,R.id.bookname})
    public void Onclick(View view){
        switch (view.getId()){
            case R.id.commentwrapper:
                changeToWriteComment(getShareAllHelper);
                break;
            case R.id.like:
                addLike(thisShareId);
                break;
            case R.id.bookname:
                if(getShareAllHelper.getIsbn()==null){
                    MyToast.toast("没有信息");
                }else {
                    ShowShareBookDetailDialog showShareBookDetailDialog = new ShowShareBookDetailDialog(this, R.style.Theme_AppCompat_Dialog_Alert, getShareAllHelper.getIsbn());
                    showShareBookDetailDialog.show();
                } break;
        }
    }

    @Override
    public void getShareStar(int share_id) {
        isShareStarLoaded = true;
        clearLikeList();
        GetShareStarPresenter getShareStarPresenter = new GetShareStarPresenter(this);
        getShareStarPresenter.getShareStar(share_id);
    }

    @Override
    public void getShareStarsuccess(List<GetShareStarHelper.LIkeUserData> list) {
       likeList.addAll(list);
       loadingPart.setVisibility(View.GONE);
        if(sharetablayout.getSelectedTabPosition()==1) {
            showNoComment.setVisibility(View.GONE);
        }
       if(shareCommentView.getVisibility()==View.GONE){
           shareCommentView.setVisibility(View.VISIBLE);
       }
       shareStarAdapter.setShareStarList(likeList);
       sharetablayout.getTabAt(1).setText("赞 "+likeList.size());
       lRecyclerViewAdapter2.notifyDataSetChanged();
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void getShareStarFailure(int error) {
      switch (error){
          case Constant.ERROR_LOGIN_NULL:
              sharetablayout.getTabAt(1).setText("赞 0");
              loadingPart.setVisibility(View.GONE);
              if (sharetablayout.getSelectedTabPosition()==1) {
                  shareCommentView.setVisibility(View.GONE);
                  showNoComment.setVisibility(View.VISIBLE);
              }
              swipeRefresh.setRefreshing(false);
              break;
      }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getComment(thisShareId,page_no,PAGE_SIZE);
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
                                    getShareStar(thisShareId);
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
                                    getShareStar(thisShareId);
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
