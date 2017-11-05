package com.example.book.view.AbstractView;

import java.io.File;

/**
 * Created by ljp on 2017/11/2.
 */

public interface UpLoadAvatar {
    void upLoad(String name, File path);
    void success();
    void failure(int error);
}
