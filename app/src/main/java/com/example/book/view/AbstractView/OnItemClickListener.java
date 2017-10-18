package com.example.book.view.AbstractView;

import android.view.View;

import com.example.book.EntityClass.SecondBookAllData;

import java.util.List;

/**
 * Created by ljp on 2017/10/17.
 */

public interface OnItemClickListener {
    void onItemClick(View view , int position, List<SecondBookAllData> list);
}
