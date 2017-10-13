package com.example.book.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.book.Tools.Constant;

import java.util.List;


/**
 * Created by ljp on 2017/9/7.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] findtitles=new String[]{"全部","教科书","课外书"};
    private String[] sharetitles = new String[]{"栏目","热门"};
    private int titlesType;
    public MainPagerAdapter(FragmentManager fm,List<Fragment> fragments,int titlesType) {
        super(fm);
        this.fragments = fragments;
        this.titlesType = titlesType;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(titlesType == Constant.FINDVIEWAGER){
            return findtitles[position];
        }else if(titlesType == Constant.SHAREVIEWAGER){
             return sharetitles[position];
        }
        return findtitles[position];
    }
}
