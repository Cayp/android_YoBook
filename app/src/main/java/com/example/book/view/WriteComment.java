package com.example.book.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.book.Base.BaseActivity;
import com.example.book.Presenter.WriteCommentPresenter;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyToast;
import com.example.book.view.AbstractView.SendComment;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ljp on 2017/10/25.
 */

public class WriteComment extends BaseActivity implements SendComment{
    @BindView(R.id.commentSend)
    Button commentSend;
    @BindView(R.id.writeCommentContent)
    EditText commentContent;
    @BindView(R.id.writeCommentToolbar)
    Toolbar writeCommentToolbar;
    private int share_id;
    private int reply_id;
    @Override
    protected void initView(Bundle savedInstanceState) {
    initActionBar(writeCommentToolbar,true);
    share_id = getIntent().getIntExtra("share_id",0);
    reply_id = getIntent().getIntExtra("reply_id",0);
    showKeyboard();
    }

    @Override
    protected int setContentViewId() {
        return R.layout.writecomment;
    }

    @Override
    public void addComment(int share_id, String content, int reply_id) {
        WriteCommentPresenter writeCommentPresenter = new WriteCommentPresenter(this);
        writeCommentPresenter.addComment(share_id,content,reply_id);
    }

    @Override
    public void success() {
     MyToast.toast("发表评论成功");
     finish();
    }

    @Override
    public void failure(int error) {
     switch (error){
         case 1:
             MyToast.toast("未知原因错误");
             break;
         case Constant.ERROR_NO_INTERNET:
             MyToast.toast("未连接网络,请检查");
             break;
         case Constant.ERROR_JSONGETWRONG:
             MyToast.toast("Json数据解析错误");
             break;
         case 2:
             MyToast.toast("发表评论失败");
             break;
         case Constant.ERROR_LOGIN_NULL:
             MyToast.toast("你没有填评论~");
             break;
     }
    }
    @OnClick(R.id.commentSend)
    public void sendComment(View view){
        String content = commentContent.getText().toString().trim();
        addComment(share_id,content,reply_id);
    }
    public void showKeyboard(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            public void run()
            {
                InputMethodManager inputManager = (InputMethodManager)commentContent.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(commentContent, 0);
            }
        }, 100);
    }
}
