package com.example.book.view.AbstractView;

import com.example.book.EntityClass.PublishSecBookHelper;

/**
 * Created by ljp on 2017/10/4.
 */

public interface PublishSecBook {
    void sendData(PublishSecBookHelper publishSecBookHelper);
    void sendSuceess();
    void sendFailure(int error);
}
