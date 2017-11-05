package com.example.book.Presenter;

import com.example.book.Model.UploadAvatarModel;
import com.example.book.view.AbstractView.UpLoadAvatar;

import java.io.File;

/**
 * Created by ljp on 2017/11/2.
 */

public class UpLoadAvatarPresenter implements UpLoadAvatar {
    private UpLoadAvatar upLoadAvatar;
    private UploadAvatarModel uploadAvatarModel;

    public UpLoadAvatarPresenter(UpLoadAvatar upLoadAvatar) {
        this.upLoadAvatar = upLoadAvatar;
        uploadAvatarModel = new UploadAvatarModel(this);
    }

    @Override
    public void upLoad(String name, File path) {
       uploadAvatarModel.uploadAvatar(name,path);
    }

    @Override
    public void success() {
     upLoadAvatar.success();
    }

    @Override
    public void failure(int error) {
      upLoadAvatar.failure(error);
    }
}
