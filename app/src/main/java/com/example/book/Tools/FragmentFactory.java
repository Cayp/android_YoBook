package com.example.book.Tools;

import android.os.Bundle;

import com.example.book.Base.BaseFragment;

/**
 * Created by ljp on 2017/9/9.
 */


public class FragmentFactory {

    public static <T extends BaseFragment> T creatFragment(String fragmentName){
        BaseFragment fragment = null;
        try {
            fragment = (BaseFragment) Class.forName(fragmentName).newInstance();

        } catch (Exception e) {
            Mylog.e("TAG", "创建fragment异常");
        }
        return (T) fragment;

    }
//    public static <T extends BaseFragment> T createFragment(String fragmenName , Bundle content){
//
//    }
}
