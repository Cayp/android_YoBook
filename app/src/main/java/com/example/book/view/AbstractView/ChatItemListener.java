package com.example.book.view.AbstractView;

import android.view.View;

import com.example.book.EntityClass.ChatObjects;

import java.util.List;

/**
 * Created by ljp on 2017/11/13.
 */

public interface ChatItemListener {
    void onItemClick(View v , int position, List<ChatObjects> chatObjectses);
}
