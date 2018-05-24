package com.example.book.Presenter;

import com.example.book.EntityClass.SecondBookAllData;
import com.example.book.Model.GetAllSecondBookModel;
import com.example.book.view.AbstractView.PagingLoad;

import java.util.List;

/**
 * Created by ljp on 2017/9/19.
 */

public class GetBookPresenter
{   private PagingLoad pagingLoad;
    private GetAllSecondBookModel getAllSecondBookModel;
    public GetBookPresenter(PagingLoad pagingLoad) {
        getAllSecondBookModel = new GetAllSecondBookModel(this);
        this.pagingLoad = pagingLoad ;
    }
    public void requestData(int page_no ,int page_size,int typeId){
        getAllSecondBookModel.loadData(page_no,page_size,typeId);
    }
    public void succeedRequestData(List<SecondBookAllData> dataList){
        pagingLoad.succeedRequestData(dataList);
    }
    public void failRequestData(int error){pagingLoad.failRequestData(error);}
}
