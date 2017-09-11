package com.example.book.MyView;

import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.book.R;
import android.widget.RelativeLayout;
import com.example.book.Tools.FastBlur;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.MyToast;

/**
 * Created by ljp on 2017/9/3.
 * 发布淡入淡出弹出效果
 */

public class MoreWindow extends PopupWindow implements OnClickListener {
    Activity mContext;
    private int statusBarHeight;
    private Bitmap mBitmap;
    private Bitmap overlay;
    private static final String TAG = "MoreWindow";
    private Handler mHandler = new Handler();
    RelativeLayout layout;

    public MoreWindow(Activity context) {
        mContext = context;
    }

    public void init() {
        Rect frame = new Rect();
        mContext.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        statusBarHeight = frame.top;
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        overlay = null;
        mBitmap = null;
    }

    private Bitmap blur() {
        View view = mContext.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        mBitmap = view.getDrawingCache();
        float radius = 30;
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        overlay = Bitmap.createBitmap(mBitmap, 0, statusBarHeight, width, height - statusBarHeight);
        overlay = FastBlur.doBlur(overlay, (int) radius, true);
        return overlay;
    }

    public void showMoreWindow(View anchor) {
        layout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.publish, null);
        setContentView(layout);
        showAnimation(layout);
        setBackgroundDrawable(new BitmapDrawable(mContext.getResources(), blur()));
        setOutsideTouchable(true);
        setFocusable(true);
        this.setAnimationStyle(R.style.AnimBottom);
        showAtLocation(anchor, Gravity.NO_GRAVITY, 0, 0);
        getContentView().setOnClickListener(this);
        ImageView canceLicon = (ImageView) getContentView().findViewById(R.id.cancelicon);
        FrameLayout bottomWindow = (FrameLayout)getContentView().findViewById(R.id.bottomwindow);
        ImageView shareBottom = (ImageView)getContentView().findViewById(R.id.sharebutton);
        ImageView sendBokbutn = (ImageView)getContentView().findViewById(R.id.sendbokbutn);
        ImageView writeButton = (ImageView)getContentView().findViewById(R.id.writebutton);
        OnClickPublishTouchListener onClickPublishTouchListener = new OnClickPublishTouchListener(this);
        shareBottom.setOnTouchListener(onClickPublishTouchListener);
        sendBokbutn.setOnTouchListener(onClickPublishTouchListener);
        writeButton.setOnTouchListener(onClickPublishTouchListener);
        canceLicon.setOnClickListener(this);
        bottomWindow.setOnClickListener(this);

    }

    private void showAnimation(ViewGroup layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            final View child = layout.getChildAt(i);
            if (child.getId() == R.id.bottomwindow) {
                continue;
            }
            child.setVisibility(View.INVISIBLE);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    child.setVisibility(View.VISIBLE);
                    PropertyValuesHolder moveAnim = PropertyValuesHolder.ofFloat("translationY", 600, 0);
                    PropertyValuesHolder alphaAnim = PropertyValuesHolder.ofFloat("alpha", 0, 1);
                    ObjectAnimator fadeAnim = ObjectAnimator.ofPropertyValuesHolder(child, moveAnim, alphaAnim);
                    fadeAnim.setDuration(300);
                    KickBackAnimator kickAnimator = new KickBackAnimator();
                    kickAnimator.setDuration(150);
                    fadeAnim.setEvaluator(kickAnimator);
                    fadeAnim.start();
                }
            }, 50);
        }

    }

    private void closeAnimation(ViewGroup layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            final View child = layout.getChildAt(i);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    child.setVisibility(View.VISIBLE);
                    PropertyValuesHolder moveAnim = PropertyValuesHolder.ofFloat("translationY", 0, 660);
                    PropertyValuesHolder alphaAnim = PropertyValuesHolder.ofFloat("alpha", 1, 0);
                    ObjectAnimator fadeAnim = ObjectAnimator.ofPropertyValuesHolder(child, moveAnim, alphaAnim); //淡入淡出
                    fadeAnim.setDuration(300);
                    KickBackAnimator kickAnimator = new KickBackAnimator();
                    kickAnimator.setDuration(150);
                    fadeAnim.setEvaluator(kickAnimator);
                    fadeAnim.start();
                    fadeAnim.addListener(new AnimatorListener() {

                        @Override
                        public void onAnimationStart(Animator animation) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            child.setVisibility(View.GONE);
                            dismiss();
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                            // TODO Auto-generated method stub

                        }
                    });
                }
            }, 50);
        }
    }

    @Override
    public void onClick(View v) {
        closeAnimation(layout);
    }

//    OnclickPublishTouchListener 中调用
    public void publishOnClick(View view){
        switch (view.getId()){
            case R.id.sendbokbutn:
                MyToast.toast("sendbokbutn");
                break;
            case R.id.sharebutton:
                MyToast.toast("sharebutton");
                break;
            case R.id.writebutton:
                MyToast.toast("writebutton");
                break;
        }
    }
    public void changeActivity(Class<?> act){
        Intent intent = new Intent(MyApplication.getContext(),act);
        MyApplication.getContext().startActivity(intent);
        this.dismiss();
    }

}