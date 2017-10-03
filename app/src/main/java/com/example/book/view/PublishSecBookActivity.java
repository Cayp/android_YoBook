package com.example.book.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.book.Base.BaseActivity;
import com.example.book.EntityClass.PublishSecBookHelper;
import com.example.book.MyView.InputDialog;
import com.example.book.Presenter.PublishSecBookPresenter;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.MyToast;
import com.example.book.view.AbstractView.PublishSecBook;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ljp on 2017/10/2.
 */

public class PublishSecBookActivity extends BaseActivity implements PublishSecBook{
    @BindView(R.id.booknameinput)
    EditText bookName;
    @BindView(R.id.bookcontent)
    EditText bookContent;
    @BindView(R.id.sec_toolbar)
    Toolbar secToolbar;
    @BindView(R.id.sendpicture)
    ImageView sendPicture;
    @BindView(R.id.addpicture)
    ImageView addPicture;
    @BindView(R.id.typeSelect)
    RelativeLayout typeSelect;
    @BindView(R.id.paySelect)
    RelativeLayout paySelect;
    @BindView(R.id.publishbutton)
    Button publishButton;
    @BindView(R.id.tagpublish)
    RelativeLayout tagpublish;
    @BindView(R.id.publishpayway)
    RelativeLayout publishpayway;
    @BindView(R.id.tagText)
    TextView tagText;
    @BindView(R.id.payText)
    TextView payText;
    @BindView(R.id.right_icon)
    ImageView right_icon;
    @BindView(R.id.publishText)
    TextView publishText;
    @BindView(R.id.right_iconT)
    ImageView right_iconT;
    public static final int IMAGE_PICKER = 100;
    private InputDialog inputDialog;
    private ProgressBar progressBar;
    private static final String TAG = "PublishSecBookActivity";
    private PublishSecBookHelper publishSecBookHelper = new PublishSecBookHelper();
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case  R.id.okto :
                    if(TextUtils.isEmpty(inputDialog.inputPrice.getText())){
                        MyToast.toast("你没有填~");
                    }else{
                        String i = inputDialog.inputPrice.getText().toString().trim()+"￥";
                        payText.setText(i);
                        publishpayway.setVisibility(View.VISIBLE);
                        publishSecBookHelper.setPrice(i);
                        inputDialog.dismiss();
                    }
                    break;
            }
        }
    };
    @Override
    protected void initView(Bundle savedInstanceState) {
        initActionBar(secToolbar,true);
        publishText.setText("填 写");
    }

    @Override
    protected int setContentViewId() {
        return R.layout.publishsecbook;
    }
    @OnClick({R.id.addpicture,R.id.typeSelect,R.id.paySelect,R.id.publishbutton})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.addpicture:
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent,IMAGE_PICKER);
                break;
            case R.id.typeSelect:
                PopupMenu popupMenu = new PopupMenu(MyApplication.getContext(),right_icon);         //选择标签
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_typeselect,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.classbook:
                                tagText.setText("教科书");
                                tagpublish.setVisibility(View.VISIBLE);
                                publishSecBookHelper.setType_id(Constant.CLASSBOOK);
                                return true;
                            case R.id.afterclassbook:
                                tagText.setText("课外书");
                                tagpublish.setVisibility(View.VISIBLE);
                                publishSecBookHelper.setType_id(Constant.AFTERCLASSBOOK);
                                return true;
                        }
                        return false;
                    }
                });
                break;
            case R.id.paySelect:
                PopupMenu popupMenusec = new PopupMenu(MyApplication.getContext(),right_iconT);         //选择标签
                popupMenusec.getMenuInflater().inflate(R.menu.popup_menu_payway,popupMenusec.getMenu());
                popupMenusec.show();
                popupMenusec.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.talkto:
                                payText.setText("价格私聊");
                                publishSecBookHelper.setPrice("价格私聊");
                                publishpayway.setVisibility(View.VISIBLE);
                              return true;
                            case R.id.inputprice:
                              inputDialog = new InputDialog(PublishSecBookActivity.this,R.style.Theme_AppCompat_Dialog_Alert,listener);
                              inputDialog.show();
                        }
                        return false;
                    }
                });
                break;
            case R.id.publishbutton:
                  getdata();
                  sendData(publishSecBookHelper);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> list = (ArrayList<ImageItem>)data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                String imageName = list.get(0).name;
                Log.d(TAG, "photoname"+list.get(0).name);
                String imagepath = list.get(0).path;
                ImagePicker.getInstance().getImageLoader().displayImage(this,list.get(0).path,sendPicture,900,900);  //显示图片
                File imagesend = new File(imagepath);
                publishSecBookHelper.setCoverName(imageName);
                publishSecBookHelper.setBookCover(imagesend);
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void getdata(){
        publishSecBookHelper.setBookDescription(bookContent.getText().toString().trim());
        publishSecBookHelper.setBookName(bookName.getText().toString().trim());
        publishSecBookHelper.setPublishId("meiyong");
    }

    @Override
    public void sendData(PublishSecBookHelper publishSecBookHelper) {
        PublishSecBookPresenter publishSecBookPresenter = new PublishSecBookPresenter(this);
        publishSecBookPresenter.sendData(publishSecBookHelper);
    }

    @Override
    public void sendSuceess() {
      MyToast.toast("成功");
    }

    @Override
    public void sendFailure(int error) {
       switch (error){
           case Constant.ERROR_LACKDATA:
               MyToast.toast("你还有东西没有填~");
               break;
           case Constant.ERROR_LOGINFAILURE:
               MyToast.toast("发布失败");
               break;
           case Constant.ERROR_JSONGETWRONG:
               MyToast.toast("json解析异常");
               break;
       }
    }
}
