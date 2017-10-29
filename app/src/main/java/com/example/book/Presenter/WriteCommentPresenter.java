package com.example.book.Presenter;


import com.example.book.Model.WriteCommentModel;
import com.example.book.view.AbstractView.SendComment;
import com.example.book.view.WriteComment;

/**
 * Created by ljp on 2017/10/25.
 */

public class WriteCommentPresenter implements SendComment {
    private SendComment sendComment;
    private WriteCommentModel writeCommentModel;

    public WriteCommentPresenter(SendComment sendComment) {
        this.sendComment = sendComment;
        writeCommentModel = new WriteCommentModel(this);
    }

    @Override
    public void addComment(int share_id, String content, int reply_id) {
        writeCommentModel.addComment(share_id,content,reply_id);
    }

    @Override
    public void success() {
      sendComment.success();
    }

    @Override
    public void failure(int error) {
      sendComment.failure(error);
    }
}
