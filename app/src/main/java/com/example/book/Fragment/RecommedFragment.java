package com.example.book.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.book.Adapter.ChatInAdatper;
import com.example.book.Adapter.ChatListAdapter;
import com.example.book.Base.BaseFragment;
import com.example.book.Base.LazyLoadFragment;
import com.example.book.Chat.activity.ChatActivity;
import com.example.book.Chat.utils.AppUtil;
import com.example.book.EntityClass.ChatBook;
import com.example.book.EntityClass.ChatObjects;
import com.example.book.EntityClass.GetUserDataHelper;
import com.example.book.EntityClass.UnReadMessageHelper;
import com.example.book.Presenter.GetMydataPresenter;
import com.example.book.Presenter.GetUnReadPresemter;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.UrlHelper;
import com.example.book.view.AbstractView.ChatItemListener;
import com.example.book.view.AbstractView.GetData;
import com.example.book.view.AbstractView.GetUnRead;
import com.example.book.view.AbstractView.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnItemLongClickListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by ljp on 2017/9/8.
 */

public class RecommedFragment extends LazyLoadFragment implements GetUnRead{
    @BindView(R.id.chatWrapper_list)
    LRecyclerView chatWrapper_list;
    @BindView(R.id.chat_add)
    ImageView chat_add;
    private List<ChatObjects> chatObjectsList;
    private ChatListAdapter chatListAdapter = new ChatListAdapter();
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private static int index = 0;   //用于获取用户信息的标志位
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {       //当请求完毕进行ui刷新
            super.handleMessage(msg);
            if (msg.what == 1) {
                chatListAdapter.setChatObjectsList(chatObjectsList);
                chatListAdapter.setOnClickListener(new ChatItemListener() {
                    @Override
                    public void onItemClick(View v, int position, List<ChatObjects> chatObjectses) {
                       changeToChat(chatObjectses.get(position-1).getToUserId(),chatObjectses.get(position-1).getName(),chatObjectses.get(position-1).getAvatar());
                    }
                });
                if(lRecyclerViewAdapter == null) {
                    lRecyclerViewAdapter = new LRecyclerViewAdapter(chatListAdapter);
                }
                lRecyclerViewAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
                                                                    @Override
                                                                    public void onItemLongClick(View view, final int position) {
                                                                        final PopupMenu popupMenu = new PopupMenu(MyApplication.getContext(),view.findViewById(R.id.redCircle));         //选择标签
                                                                        popupMenu.getMenuInflater().inflate(R.menu.demenu, popupMenu.getMenu());
                                                                        popupMenu.show();
                                                                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                                                            @Override
                                                                            public boolean onMenuItemClick(MenuItem item) {
                                                                                switch (item.getItemId()) {
                                                                                    case R.id.delete:
                                                                                        AppUtil.deleteChatRecord(chatListAdapter.getChatObjectsList().get(position).getToUserId());
                                                                                         chatListAdapter.getChatObjectsList().remove(position);
                                                                                        chatListAdapter.notifyItemRemoved(position);
                                                                                        return true;
                                                                                }
                                                                                   return false;
                                                                            }
                                                                        });
                                                                    }
                                                                }
                        );
                chatWrapper_list.setAdapter(lRecyclerViewAdapter);
                chatWrapper_list.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
                chatWrapper_list.setLoadMoreEnabled(false);
                chatWrapper_list.refreshComplete(chatObjectsList.size());
                chatWrapper_list.setOnRefreshListener(new OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        chatObjectsList.clear();
                        chatListAdapter.clearlist();
                        chatListAdapter.notifyDataSetChanged();
                        chatObjectsList = getChatList();
                        if(chatObjectsList!=null&&chatObjectsList.size()!=0){
                            getUserData(chatObjectsList.get(0).getToUserId());
                        }
                    }
                });

            }
        }
    };

    public RecommedFragment() {
    }

    @Override
    public void fetchData() {

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        chatObjectsList = getChatList();
        if(chatObjectsList!=null&&chatObjectsList.size()!=0) {
            getUserData(chatObjectsList.get(0).getToUserId());
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.chat_layout;
    }


    @OnClick(R.id.chat_add)
     public  void  chatAddClick(View view){
        changeActivity(ChatActivity.class,2);
    }

    @Override
    public void getUnRead() {
        GetUnReadPresemter getUnReadPresemter = new GetUnReadPresemter(this);
        getUnReadPresemter.getUnRead();

    }

    @Override
    public void success(List<UnReadMessageHelper.UnReadChats> unReadChats) {
      for(int i =0;i < unReadChats.size();i++){
          int userId = unReadChats.get(i).getFromId();
          ChatBook chatBook = new ChatBook();
          chatBook.setToUserId(userId);
          chatBook.setContent(unReadChats.get(i).getContent());
          chatBook.save();
          if(!AppUtil.islistExist(""+userId)){
              ChatObjects chatObjects = new ChatObjects();
              chatObjects.setToUserId(userId);
              chatObjects.save();                      //储存聊天列表
          }
      }
    }

    @Override
    public void failure(int error) {

    }
    private List<ChatObjects> getChatList(){
        return DataSupport.findAll(ChatObjects.class);
    }
    private void getUserData(int id){
        try{
            OkHttpUtils.post().url(UrlHelper.GETUSERINFO).addParams("user_id",""+id)
                              .build()
                               .execute(new StringCallback() {
                                   @Override
                                   public void onError(Call call, Exception e, int id) {

                                   }

                                   @Override
                                   public void onResponse(String response, int id) {
                                    GetUserDataHelper getUserDataHelper = new Gson().fromJson(response,GetUserDataHelper.class);
                                    if(getUserDataHelper.getCode()==20000){
                                        chatObjectsList.get(index).setName(getUserDataHelper.getData().getUsername());
                                        chatObjectsList.get(index).setAvatar(getUserDataHelper.getData().getAvatar());
                                        ++index;
                                        if(index < chatObjectsList.size()){
                                        getUserData(chatObjectsList.get(index).getToUserId());
                                        }else {
                                            Message message = new Message();
                                            message.what = 1;
                                            handler.sendMessage(message);
                                            index = 0;
                                        }

                                    }
                                   }
                               });

        }catch (JsonIOException j){
            j.printStackTrace();
        }
    }
}
