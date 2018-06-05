package com.example.book.Presenter;

import com.example.book.EntityClass.GetShareAllHelper;
import com.example.book.EntityClass.UserDataid_Icon;
import com.example.book.Model.GetShareAllModel;
import com.example.book.view.AbstractView.GetShare;

import java.util.List;

/**
 * Created by ljp on 2017/10/14.
 */

public class GetShareAllPresenter implements GetShare{
    private GetShare getShare;
    private GetShareAllModel getShareAllModel;

    public GetShareAllPresenter(GetShare getShare) {
        this.getShare = getShare;
        getShareAllModel = new GetShareAllModel(this);
    }

    @Override
    public void getShare(int page_no, int page_size) {
//    getShareAllModel.getShare(page_no,page_size);
    }

    @Override
    public void failRequestData(int error) {
     getShare.failRequestData(error);
    }

    @Override
    public void succeedRequestData(List<GetShareAllHelper> dataList) {
      getShare.succeedRequestData(dataList);
    }

    @Override
    public void setUserNameIcon(UserDataid_Icon userNameIcon) {
      getShare.setUserNameIcon(userNameIcon);
    }
}
