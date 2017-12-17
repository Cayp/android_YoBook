package com.example.book.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.book.Base.BaseActivity;
import com.example.book.Chat.utils.AppUtil;
import com.example.book.EntityClass.BookDetailHelper;
import com.example.book.EntityClass.Note;
import com.example.book.EntityClass.NoteBook;
import com.example.book.MyView.InputDialog;
import com.example.book.MyView.ShowNoteDialog;
import com.example.book.MyView.ShowShareBookDetailDialog;
import com.example.book.Presenter.GetBookDetailPresenter;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyToast;
import com.example.book.view.AbstractView.GetBookDetail;
import com.example.book.view.AbstractView.GetIsbnListener;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ljp on 2017/11/14.
 */

public class PublishNoteActivity extends BaseActivity implements GetBookDetail {
    @BindView(R.id.pShareToolbar)
    Toolbar toolbar;
    @BindView(R.id.addwrapper)
    RelativeLayout addwrapper;
    @BindView(R.id.touchToDetail)
    TextView touchToDetail;
    @BindView(R.id.bookname)
    TextView bookname;
    @BindView(R.id.inputTitle)
    EditText inputTitle;
    @BindView(R.id.inputShareContent)
    EditText inputNote;
    @BindView(R.id.zxingScan)
    Button zxingScan;
    @BindView(R.id.inputIsbn)
    Button inputIsbn;
    @BindView(R.id.publishbutton)
    Button publishbutton;
    private String isbn;
    private boolean isNeedLoad = false;
    private InputDialog inputDialog;
    private BookDetailHelper bookDetailHelper = new BookDetailHelper();
    private ProgressDialog progressDialog;
    private ShowNoteDialog showNoteDialog;
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
    private GetIsbnListener getIsbnListener = new GetIsbnListener() {
        @Override
        public void chooseIsbn(View view, int position, List<NoteBook> noteBookList) {
            isNeedLoad = true;
            showNoteDialog.dismiss();
            bookname.setText(noteBookList.get(position).getName());
            bookDetailHelper.setIsbn13(noteBookList.get(position).getIsbn());
            isbn = noteBookList.get(position).getIsbn();
        }
    };
    @Override
    protected void initView(Bundle savedInstanceState) {
        initActionBar(toolbar,true);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("");
        progressDialog.setMessage("正在获取书信息..");
    }

    @Override
    protected int setContentViewId() {
        return R.layout.publishnote;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            isbn = bundle.getString("result");
            getDetail(isbn);
        } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }


    @OnClick({R.id.zxingScan,R.id.inputIsbn,R.id.touchToDetail,R.id.addwrapper,R.id.publishbutton})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.zxingScan:
                if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},1);
                }else {
                    Intent openCameraIntent = new Intent(this, CaptureActivity.class);
                    startActivityForResult(openCameraIntent, 0);
                }
                break;
            case R.id.inputIsbn:
                inputDialog = new InputDialog(this,R.style.Theme_AppCompat_Dialog_Alert,onClickListener,"请输入13位的isbn码");
                inputDialog.show();
                break;
            case R.id.touchToDetail:
                if(isNeedLoad){
                    ShowShareBookDetailDialog showShareBookDetailDialog = new ShowShareBookDetailDialog(this, R.style.Theme_AppCompat_Dialog_Alert, isbn);
                    showShareBookDetailDialog.show();
                }else {
                    if (bookDetailHelper == null) {
                        MyToast.toast("没有信息");
                    } else {
                        ShowShareBookDetailDialog showShareBookDetailDialog = new ShowShareBookDetailDialog(this, R.style.Theme_AppCompat_Dialog_Alert, bookDetailHelper);
                        showShareBookDetailDialog.show();
                    }
                }break;
            case R.id.addwrapper:
                showNoteDialog = new ShowNoteDialog(this,R.style.Theme_AppCompat_Dialog_Alert,getIsbnListener);
                showNoteDialog.show();
                break;
            case R.id.publishbutton:
                if(TextUtils.isEmpty(inputTitle.getText().toString().trim())||TextUtils.isEmpty(inputNote.getText().toString().trim())){
                    if(bookDetailHelper.getIsbn13()==null){MyToast.toast("无");}
                    MyToast.toast("还有东西没填");
                }else if(bookDetailHelper.getIsbn13()==null){MyToast.toast("无");}
                else {
                    String isbn = bookDetailHelper.getIsbn13();
                    List<NoteBook> exitBook = DataSupport.where("isbn = ?",isbn).find(NoteBook.class);
                    if(exitBook.size()==0){
                        NoteBook noteBook = new NoteBook();
                        noteBook.setName(bookDetailHelper.getTitle());
                        noteBook.setCoverUrl(bookDetailHelper.getImage());
                        noteBook.setIsbn(bookDetailHelper.getIsbn13());
                        noteBook.save();
                    }
                    List<Note> noteList = DataSupport.where("isbn = ?",isbn).find(Note.class);
                    Note note = new Note();
                    note.setIsbn(isbn);
                    note.setContent(inputTitle.getText().toString().trim());
                    note.setId(noteList.size()+1);
                    note.setTime(AppUtil.getNowTime());
                    note.setTitle(inputTitle.getText().toString().trim());
                    note.setContent(inputNote.getText().toString().trim());
                    note.save();
                    MyToast.toast("纪录成功");
                    this.finish();
                }
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
        isNeedLoad = false;
        bookname.setText(bookDetailHelper.getTitle());
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
}
