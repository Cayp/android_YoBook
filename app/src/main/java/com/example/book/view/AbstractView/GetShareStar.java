package com.example.book.view.AbstractView;

import com.example.book.EntityClass.GetShareStarHelper;

import java.util.List;

/**
 * Created by ljp on 2017/10/26.
 */

public interface GetShareStar {
    void getShareStar(int share_id);
    void getShareStarsuccess(List<GetShareStarHelper.LIkeUserData> list);
    void getShareStarFailure(int error);
}
