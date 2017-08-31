package com.example.book.view;

import android.os.Bundle;
import android.view.View;

import com.example.book.Base.BaseActivity;
import com.example.book.MyView.NavbarView;
import com.example.book.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ljp on 2017/8/11.
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.Navbar_find)
    NavbarView navbar_find ;
    @BindView(R.id.Navbar_great)
    NavbarView navbar_great ;
    @BindView(R.id.Navbar_recomd)
    NavbarView navbar_recomd ;
    @BindView(R.id.Navbar_me)
    NavbarView navbar_me ;
    ArrayList<NavbarView> fourtablist = new ArrayList<NavbarView>();
    @Override
    protected void initView(Bundle savedInstanceState) {
     setNavbarobj();
    }

    @Override
    protected int setContentViewId() {
        return R.layout.mainactivity;
    }
    @OnClick({R.id.Navbar_me,R.id.Navbar_recomd,R.id.Navbar_great,R.id.Navbar_find})
    public void tabOnClick(View view){
        switch (view.getId()){
            case R.id.Navbar_find:
                selectTab(0);
                break;
            case R.id.Navbar_great:
                selectTab(1);
                break;
            case R.id.Navbar_recomd:
                selectTab(2);
                break;
            case R.id.Navbar_me:
                selectTab(3);
                break;
        }
    }

    /**
     * 得到选中的tab
     * @param tab
     */
    public void selectTab(int tab) {
     fourtablist.get(tab).setSelected(true);  //为选中状态
     for(int i=0;i<4;i++){
            if(i!=tab)
                fourtablist.get(i).setSelected(false);
        }
    }
    public void setNavbarobj(){
        fourtablist.add(navbar_find);
        fourtablist.add(navbar_great);
        fourtablist.add(navbar_recomd);
        fourtablist.add(navbar_me);
    }
}
