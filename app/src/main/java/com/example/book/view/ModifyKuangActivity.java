package com.example.book.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.book.Base.BaseActivity;
import com.example.book.R;
import com.example.book.Tools.Constant;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ljp on 2017/11/3.
 */

public class ModifyKuangActivity extends BaseActivity {
    @BindView(R.id.modifyToolbar)
    Toolbar toolbar;
    @BindView(R.id.modifytoolbarText)
    TextView modifytoolbarText;
    @BindView(R.id.modifyeditView)
    EditText modifyeditView;
    @BindView(R.id.hintText)
    TextView hintText;
    @BindView(R.id.modifySend)
    Button modifySend;
    private int modifyWhat ;
    private String modifyData;
    private boolean isEdit = false;
    private  int white = 0xffffffff;
    private int gray = 0xffdddddd;
    @Override
    protected void initView(Bundle savedInstanceState) {
    initActionBar(toolbar,true);
    modifyWhat = getIntent().getIntExtra("modifyWhat",0);
    modifyData = getIntent().getStringExtra("modifyContent");
    modifySend.setPressed(true);
    modifySend.setClickable(false);
    modifySend.setTextColor(gray);
    initdata(modifyWhat,modifyData);
    showKeyboard();
    modifyeditView.setSelection(modifyData.length());
    modifyeditView.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
         modifySend.setPressed(false);
         modifySend.setClickable(true);
         modifySend.setTextColor(white);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    });
    }

    @Override
    protected int setContentViewId() {
        return R.layout.modifykuang;
    }
    private void initdata(int modifyWhat,String modifyData){
        modifyeditView.setText(modifyData);
        switch (modifyWhat){
            case Constant.MODIFYNAME:
                modifytoolbarText.setText("更改用户名");
                hintText.setText("**取个好听的用户名吧");
                break;
            case Constant.MODIFYJIANJIE:
                modifytoolbarText.setText("更改简介");
                hintText.setText("**设置简介，让别人更好了解你");
                break;
        }
    }
    @OnClick(R.id.modifySend)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.modifySend:
                Intent intent = new Intent();
                intent.putExtra("modifyContent",modifyeditView.getText().toString().trim());
                setResult(Constant.RESULT_OK,intent);
                finish();
                break;

        }
    }
    public void showKeyboard(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            public void run()
            {
                InputMethodManager inputManager = (InputMethodManager)modifyeditView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(modifyeditView, 0);
            }
        }, 100);
    }
}
