package com.example.book.Presenter;

import com.example.book.EntityClass.GetShareStarHelper;
import com.example.book.Model.GetShareStarModel;
import com.example.book.view.AbstractView.GetShareStar;

import java.util.List;

/**
 * Created by ljp on 2017/10/26.
 */

public class GetShareStarPresenter implements GetShareStar{
    private  GetShareStar getShareStar;
    private GetShareStarModel getShareStarModel;

    public GetShareStarPresenter(GetShareStar getShareStar) {
        this.getShareStar = getShareStar;
        getShareStarModel = new GetShareStarModel(this);
    }

    @Override
    public void getShareStar(int share_id) {
    getShareStarModel.getShareStar(share_id);
    }

    @Override
    public void getShareStarsuccess(List<GetShareStarHelper.LIkeUserData> list) {
     getShareStar.getShareStarsuccess(list);
    }

    @Override
    public void getShareStarFailure(int error) {
    getShareStar.getShareStarFailure(error);
    }
}
