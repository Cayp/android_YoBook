package com.example.book.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.book.Adapter.MainPagerAdapter;
import com.example.book.Base.BaseActivity;
import com.example.book.Chat.utils.AppUtil;
import com.example.book.Fragment.FindFragment;
import com.example.book.Fragment.MyDataFragment;
import com.example.book.Fragment.RecommedFragment;
import com.example.book.Fragment.ShareFragment;
import com.example.book.MyView.MoreWindow;
import com.example.book.MyView.NavbarView;
import com.example.book.MyView.NoScrollViewPager;
import com.example.book.R;
import com.example.book.Tools.FragmentFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @BindView(R.id.menubutton)
    ImageView  menubutton;
    @BindView(R.id.pager)
    NoScrollViewPager viewPager;
    MoreWindow mMoreWindow;
    View layout;
    MainPagerAdapter mainPagerAdapter ;
    ArrayList<NavbarView> fourtablist = new ArrayList<NavbarView>();
    List<Fragment> fragmentList = new ArrayList<Fragment>();
    @Override
    protected void initView(Bundle savedInstanceState) {
     layout = LayoutInflater.from(this).inflate(R.layout.mainactivity,null);
     setNavbarobj();
     setFragments();
     setViewPager();
     firstRunSelect(0);

    @Override
    protected int setContentViewId() {
        return R.layout.mainactivity;
    }
    @OnClick({R.id.Navbar_me,R.id.Navbar_recomd,R.id.Navbar_great,R.id.Navbar_find,R.id.menubutton})
    public void tabOnClick(View view){
        switch (view.getId()){
            case R.id.Navbar_find:
                selectTab(0);
                viewPager.setCurrentItem(0,false);
                break;
            case R.id.Navbar_great:
                selectTab(1);
                viewPager.setCurrentItem(1,false);
                break;
            case R.id.Navbar_recomd:
                selectTab(2);
                viewPager.setCurrentItem(2,false);
                break;
            case R.id.Navbar_me:
                selectTab(3);
                viewPager.setCurrentItem(3,false);
                break;
            case R.id.menubutton:
              showMoreWindow(layout);
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
    private void showMoreWindow(View view){
        if (null == mMoreWindow) {
            mMoreWindow = new MoreWindow(this);
        }
        mMoreWindow.init();
        mMoreWindow.showMoreWindow(view);
    }
    private void setFragments(){
        fragmentList.add(FragmentFactory.creatFragment(FindFragment.class.getName()));
        fragmentList.add(FragmentFactory.creatFragment(ShareFragment.class.getName()));
        fragmentList.add(FragmentFactory.creatFragment(RecommedFragment.class.getName()));
        fragmentList.add(FragmentFactory.creatFragment(MyDataFragment.class.getName()));
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(),fragmentList);
    }
    private void setViewPager(){
        viewPager.setAdapter(mainPagerAdapter);
    }
    private void firstRunSelect(int tab){
        selectTab(tab);
        viewPager.setCurrentItem(tab);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppUtil.saveTime(this);//退出应用，更新最近登录时间
    }
}
