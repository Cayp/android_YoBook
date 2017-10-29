package com.example.book.view.AbstractView;

import com.example.book.EntityClass.GetCommentHelper;
import com.example.book.EntityClass.UserDataid_Icon;

import java.util.List;

/**
 * Created by ljp on 2017/10/22.
 */

public interface GetComment {
    void getComment(int share_id,int page_no,int page_size);
    void getCommentSucceed(List<GetCommentHelper.CommentItem> list);
    void getCommentFailure(int error);
    void getUserData(UserDataid_Icon userDataid_icon);
}
