package com.example.book.MyView;

import android.content.Context;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.book.R;

/**
 * Created by ljp on 2017/8/26.
 */

public class NavbarView extends RelativeLayout {
    private ImageView icon ;
    private TextView  text ;
    private int mtextSize = 15 ;
    private String mtext = "初始化";
    private int mTextColor = 0x000000;
    private int backGroundColor = 0xffffff;
    private Drawable mIcon;
    public NavbarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.navbarview,this,true);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.NavbarView);
        int n = typedArray.getIndexCount();
        for(int i=0;i<n;i++){
            int atrr = typedArray.getIndex(i);
            switch (atrr){
                case R.styleable.NavbarView_backGroundColor:
                    backGroundColor = typedArray.getColor(atrr,backGroundColor);
                    break;
                case R.styleable.NavbarView_mIcon:
                    mIcon = typedArray.getDrawable(atrr);
                    break;
                case R.styleable.NavbarView_mText:
                    mtext = typedArray.getString(atrr);
                    break;
                case R.styleable.NavbarView_mTextColor:
                    mTextColor = typedArray.getColor(atrr,mTextColor);
                    break;
                case R.styleable.NavbarView_mTextSize:
                    mtextSize  = typedArray.getDimensionPixelSize(atrr,mtextSize);
                    break;
            }
        }
        typedArray.recycle();
        icon = (ImageView)findViewById(R.id.navbar_icon);
        text = (TextView)findViewById(R.id.navbar_text);
        icon.setImageDrawable(mIcon);
        text.setText(mtext);
        text.setTextSize(mtextSize);
        text.setTextColor(mTextColor);
        setBackgroundColor(backGroundColor);
    }
    public void setSelected(boolean is){
        icon.setSelected(is);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
