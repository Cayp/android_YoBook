package com.example.book.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.book.Base.BaseActivity;
import com.example.book.Chat.utils.AppUtil;
import com.example.book.EntityClass.GetUserDataHelper;
import com.example.book.Presenter.UpLoadAvatarPresenter;
import com.example.book.Presenter.UpLoadUserDataPresenter;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.MyToast;
import com.example.book.Tools.UrlHelper;
import com.example.book.view.AbstractView.UpLoadAvatar;
import com.example.book.view.AbstractView.UpLoadData;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.senab.photoview.log.LoggerDefault;

import static com.example.book.view.PublishSecBookActivity.IMAGE_PICKER;

/**
 * Created by ljp on 2017/11/2.
 */

public class ModifyUserDataActivity extends BaseActivity implements UpLoadAvatar,UpLoadData{
   @BindView(R.id.modifyToolbar)
    Toolbar modifyToolbar;
    @BindView(R.id.Rehead)
    RelativeLayout Rehead;
    @BindView(R.id.ReName)
    RelativeLayout ReName;
    @BindView(R.id.Rejianjie)
    RelativeLayout Rejianjie;
    @BindView(R.id.ReSex)
    RelativeLayout ReSex;
    @BindView(R.id.modifyHeadIcon)
    CircleImageView modifyHeadIcon;
    @BindView(R.id.modifyName)
    TextView modifyName;
    @BindView(R.id.modifyjianjie)
    TextView modifyjianjie;
    @BindView(R.id.modifySex)
    TextView modifySex;
    private GetUserDataHelper.UserData getUserData;
    private ProgressDialog progressDialog;
    private String saveName;               //保存修改错误之前的数据
    private String jianjie;
    private String sex;
    private static final String TAG = "ModifyUserDataActivity";
    @Override
    protected void initView(Bundle savedInstanceState) {
     initActionBar(modifyToolbar,true);
     getUserData = getIntent().getParcelableExtra("userData");
     initData(getUserData);
     AppUtil.setImagePicker(1,false,false,Constant.CIRCLE);
     progressDialog = new ProgressDialog(this);
     progressDialog.setMessage("正在保存...");
     progressDialog.setTitle("");
    }

    @Override
    protected int setContentViewId() {
        return R.layout.avtivitymodifyuserdata;
    }
    @OnClick({R.id.ReSex,R.id.Rehead,R.id.Rejianjie,R.id.ReName})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ReSex:
                PopupMenu popupMenu = new PopupMenu(this,modifySex);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_sex,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.m:
                                if(getUserData.getSex().equals("m")){
                                }else {
                                    modifySex.setText("男");
                                    getUserData.setSex("m");
                                    upLoadData(getUserData);
                                }
                                break;
                            case R.id.f:
                                if(getUserData.getSex().equals("f")){
                                }else {
                                modifySex.setText("女");
                                getUserData.setSex("f");
                                    upLoadData(getUserData);
                                }
                                break;
                        }
                        return false;
                    }
                });
                break;
            case R.id.Rehead:
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent,IMAGE_PICKER);
                break;
            case R.id.Rejianjie:
                Intent intent1 = new Intent(this,ModifyKuangActivity.class);
                intent1.putExtra("modifyWhat",Constant.MODIFYJIANJIE);
                intent1.putExtra("modifyContent",getUserData.getBrief());
                startActivityForResult(intent1,Constant.MODIFYJIANJIE);
                break;
            case R.id.ReName:
                Intent intent2 = new Intent(this,ModifyKuangActivity.class);
                intent2.putExtra("modifyWhat",Constant.MODIFYNAME);
                intent2.putExtra("modifyContent",getUserData.getUsername());
                startActivityForResult(intent2,Constant.MODIFYNAME);
                break;
        }
    }


    private void initData(GetUserDataHelper.UserData getUserData){
        Picasso.with(this).load(UrlHelper.GETAVATAR  + getUserData.getAvatar())
                               .into(modifyHeadIcon);
        modifyName.setText(getUserData.getUsername());
        if(TextUtils.isEmpty(getUserData.getBrief())){
            modifyjianjie.setText("");
        }else {
            modifyjianjie.setText(getUserData.getBrief());
        }
        if(getUserData.getSex().equals("m")){
            modifySex.setText("男");
        }else {
            modifySex.setText("女");
        }
        sex = getUserData.getSex();
        saveName = getUserData.getUsername();
        jianjie = getUserData.getBrief();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "Myzhao"+requestCode);
        switch (requestCode) {
            case IMAGE_PICKER:
            if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
                if (data != null && requestCode == IMAGE_PICKER) {
                    ArrayList<ImageItem> list = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                    String imagepath = list.get(0).path;
                    Log.d(TAG, "tupianpath"+list.get(0).name);
                    getUserData.setAvatar(imagepath);
                    File imagesend = new File(imagepath);
                    progressDialog.show();
                    upLoad(imagepath, imagesend);

                } else {
                    Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case Constant.MODIFYNAME:
                if(resultCode == Constant.RESULT_OK){
                    String content = data.getStringExtra("modifyContent");
                    getUserData.setUsername(content);
                    upLoadData(getUserData);
                }
                break;
            case Constant.MODIFYJIANJIE:
                if(resultCode  == Constant.RESULT_OK){
                    String content2 = data.getStringExtra("modifyContent");
                    getUserData.setBrief(content2);
                    upLoadData(getUserData);
                }
                break;
        }
    }

    @Override
    public void upLoad(String name, File path) {
        UpLoadAvatarPresenter upLoadAvatarPresenter = new UpLoadAvatarPresenter(this);
        upLoadAvatarPresenter.upLoad(name,path);
    }

    @Override
    public void success() {
      Picasso.with(this).load("file://"+getUserData.getAvatar())
                        .into(modifyHeadIcon);
      if(progressDialog.isShowing()){
          progressDialog.dismiss();
      }
    }

    @Override
    public void failure(int error) {
     progressDialog.dismiss();
        switch (error){
        case Constant.ERROR_NO_INTERNET:
            MyToast.toast("没连网");
            reset();
            break;
        case Constant.ERROR_JSONGETWRONG:
            MyToast.toast("Json解析错误");
            reset();
            break;
        case 1:
            MyToast.toast("未知原因");
            reset();
            break;
        case Constant.ERROR_LOGIN_NULL:
            MyToast.toast("用户名不可空");
            reset();
            break;
    }
    }

    @Override
    public void upLoadData(GetUserDataHelper.UserData getUserData) {
        progressDialog.show();
        UpLoadUserDataPresenter upLoadUserDataPresenter = new UpLoadUserDataPresenter(this);
        upLoadUserDataPresenter.upLoadData(getUserData);
    }

    @Override
    public void sendSuccess() {
        saveContent();
        progressDialog.dismiss();
        MyToast.toast("修改成功");
    }

    public void reset(){                 //发送失败重置
                getUserData.setSex(sex);
                getUserData.setUsername(saveName);
                getUserData.setBrief(jianjie);
        modifyName.setText(getUserData.getUsername());
        if(TextUtils.isEmpty(getUserData.getBrief())){
            modifyjianjie.setText("");
        }else {
            modifyjianjie.setText(getUserData.getBrief());
        }
        if(getUserData.getSex().equals("m")){
            modifySex.setText("男");
        }else {
            modifySex.setText("女");
        }
    }
    private void saveContent(){
                 sex = getUserData.getSex();
                saveName = getUserData.getUsername();
                jianjie = getUserData.getBrief();
            modifyName.setText(getUserData.getUsername());
        if(TextUtils.isEmpty(getUserData.getBrief())){
            modifyjianjie.setText("");
        }else {
            modifyjianjie.setText(getUserData.getBrief());
        }
        if(getUserData.getSex().equals("m")){
            modifySex.setText("男");
        }else {
            modifySex.setText("女");
        }

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(Constant.RESULT_OK,intent);
        finish();
    }
}
