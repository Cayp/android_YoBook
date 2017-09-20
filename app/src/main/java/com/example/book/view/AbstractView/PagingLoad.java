package com.example.book.view.AbstractView;

import com.example.book.EntityClass.SecondBookAllData;

import java.util.List;

/**
 * Created by ljp on 2017/9/19.
 */

public interface PagingLoad {
    void requestData();
    void failRequestData(int Error);
    void succeedRequestData(List<SecondBookAllData> dataList);
}
