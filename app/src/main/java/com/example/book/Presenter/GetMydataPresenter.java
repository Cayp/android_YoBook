package com.example.book.Presenter;

import com.example.book.EntityClass.GetUserDataHelper;
import com.example.book.Model.GetMydataModel;
import com.example.book.view.AbstractView.GetData;

/**
 * Created by ljp on 2017/10/12.
 */

public class GetMydataPresenter implements GetData {
    private GetData getData;
    private GetMydataModel getMydataModel;

    public GetMydataPresenter(GetData getData) {
        this.getData = getData;
        getMydataModel = new GetMydataModel(this);
    }

    @Override
    public void getdata(int userId) {
        getMydataModel.getData(userId);
    }

    @Override
    public void success(GetUserDataHelper getUserDataHelper) {
        getData.success(getUserDataHelper);
    }

    @Override
    public void failure(int error) {
          getData.failure(error);
    }
}
