package com.example.book.Fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.book.Adapter.MainPagerAdapter;
import com.example.book.Base.BaseFragment;
import com.example.book.Base.LazyLoadFragment;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.FragmentFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ljp on 2017/9/8.
 */

public class ShareFragment extends LazyLoadFragment{
    @BindView(R.id.sharetablayout)
    TabLayout shareTabLayout ;
    @BindView(R.id.sharePager)
    ViewPager sharePager;
    MainPagerAdapter mainPagerAdapter ;
    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public void fetchData() {
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
     setFragmentList();
     sharePager.setAdapter(mainPagerAdapter);
     shareTabLayout.setupWithViewPager(sharePager);
     shareTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == shareTabLayout.getTabAt(0)) {
                    sharePager.setCurrentItem(0);
                } else if (tab == shareTabLayout.getTabAt(1)) {
                    sharePager.setCurrentItem(1);
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.share_fragment;
    }
    private void setFragmentList(){
        fragmentList.add(FragmentFactory.creatFragment(ShareAllFragment.class.getName()));
        fragmentList.add(FragmentFactory.creatFragment(ShareHotFragment.class.getName()));
        mainPagerAdapter = new MainPagerAdapter(getFragmentManager(),fragmentList, Constant.SHAREVIEWAGER);
    }
}
