package com.example.book.MyView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.book.Adapter.NotepublishAdapter;
import com.example.book.EntityClass.NoteBook;
import com.example.book.R;
import com.example.book.Tools.MyApplication;
import com.example.book.view.AbstractView.GetIsbnListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ljp on 2017/11/14.
 */

public class ShowNoteDialog extends Dialog {
    @BindView(R.id.cancelwrapper)
    RelativeLayout cancel;
    @BindView(R.id.exitGrid)
    RecyclerView exitGrid;
    @BindView(R.id.cancelIcon)
    ImageView cancelIcon;
    private Activity context;
    private GetIsbnListener getIsbnListener;
    public ShowNoteDialog(@NonNull Activity context, @StyleRes int themeResId, GetIsbnListener getIsbnListener) {
        super(context, themeResId);
        this.context = context;
        this.getIsbnListener = getIsbnListener;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.chooseexit);
        ButterKnife.bind(this);
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        p.width = (int)(dm.widthPixels*0.99999);
        p.height = (int)(dm.heightPixels*0.9);
        dialogWindow.setAttributes(p);
        this.setCancelable(true);
        getList();
    }
    private void getList(){
        List<NoteBook> noteBooks = DataSupport.findAll(NoteBook.class);
        Log.e("getList: ","noteBooks"+noteBooks.size() );
        NotepublishAdapter notepublishAdapter = new NotepublishAdapter();
        notepublishAdapter.setNoteBookList(noteBooks);
        Log.e("getList: ",""+noteBooks.size() );
        LinearLayoutManager manager = new LinearLayoutManager(MyApplication.getContext());
        exitGrid.setLayoutManager(manager);
        notepublishAdapter.setGetIsbnListener(getIsbnListener);
        exitGrid.setAdapter(notepublishAdapter);
    }
    @OnClick({R.id.cancelwrapper,R.id.cancelIcon})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.cancelwrapper:
                this.dismiss();
                break;
            case R.id.cancelIcon:
                this.dismiss();
                break;
        }
    }
}
