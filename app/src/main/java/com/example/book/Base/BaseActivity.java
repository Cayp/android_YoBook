package com.example.book.Base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    private ActionBar actionBar ;
    protected abstract void initView(Bundle savedInstanceState);
    protected abstract int setContentViewId();

    protected final static int statusbarcolor = Color.parseColor("#000000");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(statusbarcolor);            //设置通知栏背景黑色

        }
        setContentView(setContentViewId());
        ButterKnife.bind(this);
        initView(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    protected void initActionBar(Toolbar toolbar , boolean isBack ){
//        toolbar.setNavigationIcon(R.drawable.leftarrow);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(isBack);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    protected void changeActivity(Class<?> act ){
        Intent intent = new Intent(this,act);
        startActivity(intent);
    }

    protected void changeActivity(Class<?> act ,int userid){
        Intent intent = new Intent(this,act);
        intent.putExtra("userid",""+userid);
        startActivity(intent);
    }


}
