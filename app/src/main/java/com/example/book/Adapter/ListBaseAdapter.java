package com.example.book.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by ljp on 2017/9/19.
 */

public abstract class ListBaseAdapter<T> extends RecyclerView.Adapter<TradeRecyclerAdapter.ViewHolder> {
    private List<T> mDataList = new ArrayList<>();

}

