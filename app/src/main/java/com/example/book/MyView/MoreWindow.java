package com.example.book.MyView;

import android.animation.PropertyValuesHolder;
import android.content.Context;
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

import com.example.book.MyView.KickBackAnimator;
import com.example.book.MyView.OnClickPublishTouchListener;
import com.example.book.R;
import android.widget.RelativeLayout;
import com.example.book.Tools.FastBlur;
import com.example.book.Tools.MyApplication;
import com.example.book.Tools.MyToast;
import com.example.book.view.PublishSecBookActivity;

/**
 * Created by ljp on 2017/9/3.
 * 发布淡入淡出弹出效果
 */

public class MoreWindow extends PopupWindow implements OnClickListener {
    Activity mContext;
    private int statusBarHeight;
    private Bitmap mBitmap;
    private Bitmap overlay;
    private FrameLayout bottomWindow;
    private RelativeLayout publishlayout;
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
    }

    private Bitmap blur() {
        View view = mContext.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        mBitmap = view.getDrawingCache();
        float radius = 4;
        float scaleFactor = 8;
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        overlay = Bitmap.createBitmap( (int)(width/scaleFactor), (int)((height - statusBarHeight)/scaleFactor),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.translate(0,-statusBarHeight/scaleFactor);
        canvas.scale(1/scaleFactor,1/scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(mBitmap,0,0,paint);
        overlay = FastBlur.doBlur(overlay, (int) radius, false);
        view.destroyDrawingCache(); //清除缓存
        return overlay;
    }

    public void showMoreWindow(View anchor) {
        layout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.publish, null);
        publishlayout = (RelativeLayout) layout.findViewById(R.id.publishwrapper);
        setContentView(layout);
        showAnimation(publishlayout);
        setBackgroundDrawable(new BitmapDrawable(null, blur()));
        setOutsideTouchable(true);
        setFocusable(true);
        this.setAnimationStyle(R.style.AnimBottom);
        showAtLocation(anchor, Gravity.NO_GRAVITY, 0, 0);
        getContentView().setOnClickListener(this);
        ImageView canceLicon = (ImageView) getContentView().findViewById(R.id.cancelicon);
        bottomWindow = (FrameLayout)getContentView().findViewById(R.id.bottomwindow);
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
                        layout.setVisibility(View.VISIBLE);
                        PropertyValuesHolder moveAnim = PropertyValuesHolder.ofFloat("translationY", 600, 0);
                        PropertyValuesHolder alphaAnim = PropertyValuesHolder.ofFloat("alpha", 0, 1);
                        ObjectAnimator fadeAnim = ObjectAnimator.ofPropertyValuesHolder(layout, moveAnim, alphaAnim);
                        fadeAnim.setDuration(250);
                        KickBackAnimator kickAnimator = new KickBackAnimator();
                        kickAnimator.setDuration(150);
                        fadeAnim.setEvaluator(kickAnimator);
                        fadeAnim.start();
                    }

    private void closeAnimation(final ViewGroup layout) {
                        layout.setVisibility(View.VISIBLE);
                        PropertyValuesHolder moveAnim = PropertyValuesHolder.ofFloat("translationY", 0, 660);
                        PropertyValuesHolder alphaAnim = PropertyValuesHolder.ofFloat("alpha", 1, 0);
                        ObjectAnimator fadeAnim = ObjectAnimator.ofPropertyValuesHolder(layout, moveAnim, alphaAnim); //淡入淡出
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
                                layout.setVisibility(View.GONE);
                                dismiss();
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {
                                // TODO Auto-generated method stub

                            }
                        });
                    }

    @Override
    public void onClick(View v) {
        closeAnimation(publishlayout);
        bottomWindow.setVisibility(View.INVISIBLE);
        destroy();
    }

//    OnclickPublishTouchListener 中调用
    public void publishOnClick(View view){
        switch (view.getId()){
            case R.id.sendbokbutn:
                changeActivity(PublishSecBookActivity.class);
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
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getContext().startActivity(intent);
        this.dismiss();
    }
    public void destroy() {
        if (null != overlay) {
            overlay = null;
            System.gc();
        }
        if (null != mBitmap) {
            mBitmap = null;
            System.gc();
        }
    }


}