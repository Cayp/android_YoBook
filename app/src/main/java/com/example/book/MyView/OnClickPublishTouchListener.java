package com.example.book.MyView;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ljp on 2017/9/6.
 *
 * 点击菜单
 */

public class OnClickPublishTouchListener implements View.OnTouchListener {
    private ViewClickEffect mViewClickEffect = new OnClickEffectScaleAnimate();
    private MoreWindow mPopupWindow ;
    public OnClickPublishTouchListener(MoreWindow mPopupWindow) {
        this.mPopupWindow = mPopupWindow;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mViewClickEffect.onPressedEffect(view);
                view.setPressed(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                boolean isInside = ((x > 0 && x < view.getWidth()) && (y > 0 && y < view.getHeight()));
                if (view.isPressed() != isInside) {
                    view.setPressed(isInside);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                mViewClickEffect.onUnPressedEffect(view);
                view.setPressed(false);
                break;
            case MotionEvent.ACTION_UP:
                mViewClickEffect.onUnPressedEffect(view);
                if (view.isPressed()) {
                    mPopupWindow.publishOnClick(view);
                    view.setPressed(false);
                }
                break;
        }
        return true;
    }

}
