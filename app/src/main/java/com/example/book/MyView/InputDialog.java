package com.example.book.MyView;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.book.R;


/**
 * Created by ljp on 2017/10/3.
 */

public class InputDialog extends Dialog {
    private Activity context;
    private Button cancelTo;
    private Button okTo;
    public EditText inputPrice;
    private View.OnClickListener mClickListener;
    private String hint;
    public InputDialog(@NonNull Context context) {
        super(context);
    }

    public InputDialog(@NonNull Activity context, @StyleRes int themeResId, View.OnClickListener mClickListener,String hint) {
        super(context, themeResId);
        this.context =  context;
        this.mClickListener = mClickListener;
        this.hint = hint;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.inputpricedialog);
        cancelTo = (Button)findViewById(R.id.cancelto);
        okTo = (Button)findViewById(R.id.okto);
        inputPrice = (EditText)findViewById(R.id.input_pricedialog);
        inputPrice.setHint(hint);
        okTo.setOnClickListener(mClickListener);
        cancelTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        p.width = (int)(dm.widthPixels*0.8);
        dialogWindow.setAttributes(p);
        this.setCancelable(true);
    }
}
