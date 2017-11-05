package com.example.book.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.book.Base.BaseActivity;
import com.example.book.Chat.utils.AppUtil;
import com.example.book.EntityClass.BookDetailHelper;
import com.example.book.EntityClass.Imagedata;
import com.example.book.EntityClass.PublishShareHelper;
import com.example.book.MyView.InputDialog;
import com.example.book.MyView.ShowShareBookDetailDialog;
import com.example.book.Presenter.GetBookDetailPresenter;
import com.example.book.Presenter.PublishSharePresenter;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyToast;
import com.example.book.view.AbstractView.GetBookDetail;
import com.example.book.view.AbstractView.PublishShare;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.book.view.PublishSecBookActivity.IMAGE_PICKER;

public class ZxingActivity extends BaseActivity implements GetBookDetail,PublishShare {
    @BindView(R.id.zxingScan)
    Button zxingScan;
    @BindView(R.id.inputIsbn)
    Button inputIsbn;
    @BindView(R.id.touchToDetail)
    TextView touchToDetail;
    @BindView(R.id.bookname)
    TextView bookname;
    @BindView(R.id.inputShareContent)
    EditText inputShareContent;
    @BindView(R.id.pShareToolbar)
    Toolbar pShareToolbar;
    @BindView(R.id.sendpicture0)
    ImageView sendPicture0;
    @BindView(R.id.sendpicture1)
    ImageView sendPicture1;
    @BindView(R.id.publishbutton)
    Button publishbutton;
    private static final String TAG = "ZxingActivity";
    private ProgressDialog progressDialog;
    private BookDetailHelper bookDetailHelper;
    private PublishShareHelper publishShareHelper = new PublishShareHelper();
    private String isbn;
    private InputDialog inputDialog;
    private View.OnClickListener onClickListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if(TextUtils.isEmpty(inputDialog.inputPrice.getText())){
              MyToast.toast("你没有填~");
          }else if(inputDialog.inputPrice.getText().length()!=13){
              MyToast.toast("请填正确的13位isbn码");
          }else {
              getDetail(inputDialog.inputPrice.getText().toString());
              inputDialog.dismiss();
          }
        }
    };
    @Override
    protected void initView(Bundle savedInstanceState) {
        initActionBar(pShareToolbar,true);
        AppUtil.setImagePicker(2,true,true,0);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("");
        progressDialog.setMessage("正在获取书信息..");
    }

    @Override
    protected int setContentViewId() {
        return R.layout.activity_publishshare;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            isbn = bundle.getString("result");
            publishShareHelper.setIsbn(isbn);
            getDetail(isbn);
        }else if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> list = (ArrayList<ImageItem>)data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                List<Imagedata> files = new ArrayList<>();
                for(int i =0;i<list.size();i++) {
                    String imagepath = list.get(i).path;
                    File imagefile = new File(imagepath);
                    Imagedata imagedata = new Imagedata();
                    imagedata.setPath(imagepath);
                    imagedata.setCover(imagefile);
                    files.add(imagedata);                                      //储存返回的图片
                    switch (i) {
                        case 0:
                        ImagePicker.getInstance().getImageLoader().displayImage(this, imagepath,sendPicture0 , 900, 900);  //显示图片
                            break;
                        case 1:
                         ImagePicker.getInstance().getImageLoader().displayImage(this, imagepath,sendPicture1 , 900, 900);  //显示图片
                         break;
                    }
                }
                publishShareHelper.setFiles(files);
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Intent openCameraIntent = new Intent(ZxingActivity.this, CaptureActivity.class);
                    startActivityForResult(openCameraIntent, 0);
                }else {
                    MyToast.toast("你拒绝了申请权限");
                }
                break;
        }
    }
    @OnClick({R.id.zxingScan,R.id.inputIsbn,R.id.touchToDetail,R.id.addpicture,R.id.publishbutton})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.zxingScan:
                if(ActivityCompat.checkSelfPermission(ZxingActivity.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ZxingActivity.this,new String[]{Manifest.permission.CAMERA},1);
                }else {
                    Intent openCameraIntent = new Intent(ZxingActivity.this, CaptureActivity.class);
                    startActivityForResult(openCameraIntent, 0);
                }
                break;
            case R.id.inputIsbn:
                inputDialog = new InputDialog(this,R.style.Theme_AppCompat_Dialog_Alert,onClickListener,"请输入13位的isbn码");
                inputDialog.show();
                break;
            case R.id.touchToDetail:
                if(bookDetailHelper==null){
                    MyToast.toast("没有信息");
                }else {
                    ShowShareBookDetailDialog showShareBookDetailDialog = new ShowShareBookDetailDialog(this, R.style.Theme_AppCompat_Dialog_Alert,bookDetailHelper );
                    showShareBookDetailDialog.show();
                }break;
            case R.id.addpicture:
                ImagePicker.getInstance().getImageLoader().clearMemoryCache();
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent,IMAGE_PICKER);
                break;
            case R.id.publishbutton:
                progressDialog = ProgressDialog.show(this,"","正在发布...");
                progressDialog.show();
                publishShare(publishShareHelper);
                break;
        }
    }

    @Override
    public void getDetail(String isbn) {
        GetBookDetailPresenter getBookDetailPresenter = new GetBookDetailPresenter(this);
        getBookDetailPresenter.getDetail(isbn);
    }

    @Override
    public void getSuccess(BookDetailHelper bookDetailHelper) {
      this.bookDetailHelper = bookDetailHelper;
      bookname.setText(bookDetailHelper.getTitle());
      publishShareHelper.setName(bookDetailHelper.getTitle());
      publishShareHelper.setTypeId(1);
      if(progressDialog.isShowing()) {
          progressDialog.dismiss();
      }
    }

    @Override
    public void getFailure(int error) {
      switch (error){
          case Constant.CANTFOUNDBOOK:
              MyToast.toast("数据库找无此书");
              hideProgress();
             break;
          case Constant.ERROR_JSONGETWRONG:
              MyToast.toast("Json解析错误");
              hideProgress();
              break;
          case  1 :
              MyToast.toast("未知原因");
              hideProgress();
              break;
          case Constant.ERROR_NO_INTERNET:
              MyToast.toast("没网");
              hideProgress();
              break;
          case Constant.ERROR_LOGIN_NULL:
              MyToast.toast("还有东西没有填");
              hideProgress();
              break;
      }
    }
    private void hideProgress(){
        if(progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void publishShare(PublishShareHelper publishShareHelper) {
        PublishSharePresenter publishSharePresenter = new PublishSharePresenter(this);
        publishShareHelper.setContent(inputShareContent.getText().toString().trim());
        publishSharePresenter.publishShare(publishShareHelper);

    }

    @Override
    public void publishShareSuccess() {
         this.finish();
        MyToast.toast("发布成功");
        hideProgress();
    }


}