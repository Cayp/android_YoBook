package com.example.book.Chat.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.book.Adapter.ChatInAdatper;
import com.example.book.Base.BaseActivity;
import com.example.book.Chat.api.Api;
import com.example.book.Chat.entity.Notice;
import com.example.book.Chat.presenter.ChatPresenter;
import com.example.book.Chat.utils.AppUtil;
import com.example.book.Chat.utils.RxBus;
import com.example.book.R;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.MyToast;
import com.example.book.Tools.UrlHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Clanner on 2017/9/13.
 */
public class ChatActivity extends BaseActivity implements Api.SendMessage,Api.GetMessage,Api.ReadAllMessage,Api.ReadMessage{
    @BindView(R.id.chatToolbar)
    Toolbar chatToolbar;
    @BindView(R.id.chat_list)
    RecyclerView chatList;
    @BindView(R.id.edt_message)
    EditText edtMessage;
    @BindView(R.id.chat_icon)
    CircleImageView chat_icon;
    @BindView(R.id.to_name)
    TextView to_name;
//    private List<TalkRecord> talkRecordList = new ArrayList();
    private List<com.example.book.Chat.entity.Message> talkRecordList = new ArrayList<>();
    private int to_id;
    private String to_avatar;
    private String to_Name;
    private ChatPresenter chatPresenter;
    private Subscription subscription;
    private ChatInAdatper chatInAdatper;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MyToast.toast(((Notice) msg.obj).getContent());
        }
    };

    @Override
    protected int setContentViewId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initActionBar(chatToolbar,true);
        to_id = getIntent().getIntExtra("toId", 0);//传当前聊天的用户id
        to_avatar = getIntent().getStringExtra("toAvatar");
        to_Name = getIntent().getStringExtra("to_Name");
        chatPresenter = new ChatPresenter(this,this,this,this);
        getMessage();
        to_name.setText(to_Name);
        Picasso.with(MyApplication.getContext()).load(UrlHelper.GETAVATAR + to_avatar)
                .error(R.mipmap.ic_launcher_round)
                .into(chat_icon);
        subscription = RxBus.getInstance().receive(Notice.class).subscribe(new Subscriber<Notice>() {
            @Override
            public void onCompleted() {
                Log.e("DEBUG", "完成");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("DEBUG", "错误信息" + e.getMessage());
            }

            @Override
            public void onNext(Notice notice) {
                //到时换成通知栏显示
                Message message = new Message();
                message.obj = notice;
                handler.sendMessage(message);
            }
        });
    }

    @OnClick({R.id.btn_send,R.id.chat_list,R.id.backgroundchat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                String content = edtMessage.getText().toString().trim();
            if (to_id == 0) {MyToast.toast("获取用户id失败");}
            else if ("".equals(content)) {
                MyToast.toast("消息不能为空");
            } else {
                sendMessage(to_id, content);
            }
                break;
            case R.id.chat_list:
                break;
            case R.id.backgroundchat:
                InputMethodManager imm3 = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm3.hideSoftInputFromWindow(view.getWindowToken(),0);
                break;
        }

    }
    @Override
    public void sendMessage(int to_id, String content) {
        chatPresenter.sendMessage(to_id, content);
        edtMessage.setText("");
    }

    @Override
    public void sendMessageSuccess() {
        MyToast.toast("发送成功");
    }

    @Override
    public void sendMessageFailure(String message) {
        MyToast.toast(message);
    }

    @Override
    public void getMessage() {
        chatPresenter.getMessage();
    }

    @Override
    public void getMessageSuccess(List<com.example.book.Chat.entity.Message> messages) {
        talkRecordList.addAll(messages);
//        chatInAdatper = new ChatInAdatper(talkRecordList);
        chatList.setAdapter(chatInAdatper);
    }

    @Override
    public void getMessageFailure(String message) {

    }

    @Override
    public void readMessage(int from_id) {

    }

    @Override
    public void readMessageSuccess() {

    }

    @Override
    public void readMessageFailure(String message) {

    }

    @Override
    public void readAllMessage() {

    }

    @Override
    public void readAllMessageSuccess() {

    }

    @Override
    public void readAllMessageFailure(String message) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
        AppUtil.saveTime(this);//到时删掉这句
    }
}
