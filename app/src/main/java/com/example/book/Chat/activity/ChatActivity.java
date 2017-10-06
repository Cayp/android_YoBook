package com.example.book.Chat.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;

import com.example.book.Base.BaseActivity;
import com.example.book.Chat.api.Api;
import com.example.book.Chat.entity.Notice;
import com.example.book.Chat.presenter.ChatPresenter;
import com.example.book.Chat.utils.AppUtil;
import com.example.book.Chat.utils.RxBus;
import com.example.book.R;
import com.example.book.Tools.MyToast;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Clanner on 2017/9/13.
 */
public class ChatActivity extends BaseActivity implements Api.SendMessage {

    @BindView(R.id.chat_list)
    RecyclerView chatList;
    @BindView(R.id.edt_message)
    EditText edtMessage;
    private int to_id;
    private ChatPresenter chatPresenter;

    private Subscription subscription;
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
        to_id = getIntent().getIntExtra("toId", 0);//传当前聊天的用户id
        chatPresenter = new ChatPresenter(this);
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

    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        if (to_id == 0) MyToast.toast("获取用户id失败");
        String content = edtMessage.getText().toString().trim();
        if ("".equals(content)) {
            MyToast.toast("消息不能为空");
        } else {
            sendMessage(to_id, content);
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
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
        AppUtil.saveTime(this);//到时删掉这句
    }
}
