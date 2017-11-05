package com.example.book.Presenter;

import com.example.book.EntityClass.GetUserDataHelper;
import com.example.book.Model.ModifyUserInfoModel;
import com.example.book.view.AbstractView.UpLoadData;

/**
 * Created by ljp on 2017/11/2.
 */

public class UpLoadUserDataPresenter implements UpLoadData {
    private UpLoadData upLoadData;
    private ModifyUserInfoModel modifyUserInfoModel;

    public UpLoadUserDataPresenter(UpLoadData upLoadData) {
        this.upLoadData = upLoadData;
        modifyUserInfoModel = new ModifyUserInfoModel(this);
    }

    @Override
    public void upLoadData(GetUserDataHelper.UserData getUserData) {
        modifyUserInfoModel.modifyInfo(getUserData);
    }

    @Override
    public void sendSuccess() {
     upLoadData.sendSuccess();
    }

    @Override
    public void failure(int error) {
      upLoadData.failure(error);
    }
}
