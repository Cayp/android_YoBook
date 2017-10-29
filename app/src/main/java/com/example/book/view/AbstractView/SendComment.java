package com.example.book.view.AbstractView;

/**
 * Created by ljp on 2017/10/25.
 */

public interface SendComment {
    void addComment(int share_id,String content,int reply_id);
    void success();
    void failure(int error);
}
