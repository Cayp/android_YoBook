package com.example.book.Fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import com.example.book.Adapter.MainPagerAdapter;
import com.example.book.Base.BaseFragment;
import com.example.book.Base.LazyLoadFragment;
import com.example.book.R;
import com.example.book.Tools.FragmentFactory;
import com.example.book.Tools.MyToast;
import com.example.book.view.TestActivity;
import com.github.jdsjlzx.recyclerview.LRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ljp on 2017/9/8.
 */

public class FindFragment extends LazyLoadFragment {
    @BindView(R.id.cantouch)
    RelativeLayout headsearch;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.find_viewPager)
    ViewPager viewPager;
    MainPagerAdapter pagerAdapter;
    List<Fragment> fragmentList = new ArrayList<>();


    public FindFragment() {
    }

    @Override
    public void fetchData() {

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        setFragment();
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
//        setTabLayout(tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab == tabLayout.getTabAt(0)){
                    viewPager.setCurrentItem(0);
                }else if(tab == tabLayout.getTabAt(1)){
                    viewPager.setCurrentItem(1);
                }else if(tab == tabLayout.getTabAt(2)){
                    viewPager.setCurrentItem(2);
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
        return R.layout.find_fragment;
    }

    @OnClick({R.id.cantouch})
    public void onClick(View v) {
        MyToast.toast("搜索");
        changeActivity(TestActivity.class,1);
    }
    public void setFragment(){
        fragmentList.add(FragmentFactory.creatFragment(ShowoBookFragment.class.getName()));
        fragmentList.add(FragmentFactory.creatFragment(ShowTypedBookFragment.class.getName()));
        fragmentList.add(FragmentFactory.creatFragment(ShowTypeBoolFragment2.class.getName()));
        pagerAdapter = new MainPagerAdapter(getFragmentManager(),fragmentList);
    }
}
