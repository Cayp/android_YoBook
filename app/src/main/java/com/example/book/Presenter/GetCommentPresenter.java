package com.example.book.Presenter;

import com.example.book.EntityClass.GetCommentHelper;
import com.example.book.EntityClass.UserDataid_Icon;
import com.example.book.Model.GetCommentModel;
import com.example.book.view.AbstractView.GetComment;

import java.util.List;

/**
 * Created by ljp on 2017/10/23.
 */

public class GetCommentPresenter implements GetComment {
    private GetComment getComment;
    private GetCommentModel getCommentModel ;

    public GetCommentPresenter(GetComment getComment) {
        this.getComment = getComment;
        getCommentModel = new GetCommentModel(this);
    }

    @Override
    public void getComment(int share_id, int page_no, int page_size) {
    getCommentModel.GetComment(share_id,page_no,page_size);
    }

    @Override
    public void getCommentSucceed(List<GetCommentHelper.CommentItem> list) {
    getComment.getCommentSucceed(list);
    }

    @Override
    public void getCommentFailure(int error) {
    getComment.getCommentFailure(error);
    }


}
