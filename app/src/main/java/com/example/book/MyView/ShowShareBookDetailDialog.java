package com.example.book.MyView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.book.EntityClass.BookDetailHelper;
import com.example.book.Presenter.GetBookDetailPresenter;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.MyToast;
import com.example.book.view.AbstractView.GetBookDetail;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ljp on 2017/10/31.
 */

public class ShowShareBookDetailDialog extends Dialog implements GetBookDetail{
    @BindView(R.id.cancelIcon)
    ImageView cancelIcon;
    @BindView(R.id.bookCover)
    ImageView bookCover;
    @BindView(R.id.bookauthor)
    TextView bookauthor;
    @BindView(R.id.publishDate)
    TextView publishDate;
    @BindView(R.id.publishName)
    TextView publishName;
    @BindView(R.id.jianjie)
    TextView jianjie;
    @BindView(R.id.subtile)
    TextView subtile;
    @BindView(R.id.midhe)
    RelativeLayout midhe;
    @BindView(R.id.detailProgressBar)
    RelativeLayout detailProgressBar;
    @BindView(R.id.cancelwrapper)
    RelativeLayout cancelwrapper;
    private BookDetailHelper bookDetailHelper;
    private Activity context;
    private String isbn;
    private boolean needDownload;
    public ShowShareBookDetailDialog(@NonNull Activity context, @StyleRes int themeResId,BookDetailHelper bookDetailHelper){
        super(context, themeResId);
        this.context = context;
        this.bookDetailHelper = bookDetailHelper;
        needDownload = false;
    }

    public ShowShareBookDetailDialog(@NonNull Activity context, @StyleRes int themeResId,String isbn) {
        super(context, themeResId);
        this.context = context;
        this.isbn = isbn;
        needDownload = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.showsharebookdetail);
        ButterKnife.bind(this);
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        p.width = (int)(dm.widthPixels*0.99999);
        p.height = (int)(dm.heightPixels*0.9);
        dialogWindow.setAttributes(p);
        this.setCancelable(true);
        if(!needDownload) {                             //复用标志
            loadData(bookDetailHelper);
            detailProgressBar.setVisibility(View.GONE);
            midhe.setVisibility(View.VISIBLE);
        }else {
            getDetail(isbn);
        }
    }
    @OnClick({R.id.cancelIcon,R.id.cancelwrapper})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.cancelIcon:
                this.dismiss();
                break;
            case R.id.cancelwrapper:
                this.dismiss();
                break;
        }
    }

    private void loadData(BookDetailHelper bookDetailHelper){
      for(int i = 0;i < bookDetailHelper.getAuthor().size();i++){
          bookauthor.append(bookDetailHelper.getAuthor().get(i)+"  ");
      }
      publishDate.setText(bookDetailHelper.getPubdate());
      publishName.setText(bookDetailHelper.getPublisher());
      subtile.setText(bookDetailHelper.getSubtitle());
      jianjie.setText(bookDetailHelper.getSummary());
      Picasso.with(MyApplication.getContext()).load(bookDetailHelper.getImage())
                                              .fit()
                                              .error(R.mipmap.ic_launcher)
                                              .into(bookCover);
    }

    @Override
    public void getDetail(String isbn) {
        GetBookDetailPresenter getBookDetailPresenter = new GetBookDetailPresenter(this);
        getBookDetailPresenter.getDetail(isbn);
    }

    @Override
    public void getSuccess(BookDetailHelper bookDetailHelper) {
        loadData(bookDetailHelper);
        detailProgressBar.setVisibility(View.GONE);
        midhe.setVisibility(View.VISIBLE);
    }

    @Override
    public void getFailure(int error) {
      switch (error){
          case Constant.ERROR_JSONGETWRONG:
              MyToast.toast("Json解析错误");
              break;
          case  1 :
              MyToast.toast("未知原因");
              break;
          case Constant.ERROR_NO_INTERNET:
              MyToast.toast("没网");
              break;
      }
    }
}
