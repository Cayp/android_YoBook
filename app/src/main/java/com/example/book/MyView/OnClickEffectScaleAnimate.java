package com.example.book.MyView;

import android.animation.TimeInterpolator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by ljp on 2017/9/6.
 */

public class OnClickEffectScaleAnimate implements ViewClickEffect {
    private TimeInterpolator interpolator = new DecelerateInterpolator();
    private static final float scale = 0.6f;
    private static final int duration = 130;

    @Override
    public void onPressedEffect(View view) {
    view.animate().scaleX(scale).scaleY(scale).setDuration(duration).setInterpolator(interpolator);
    }

    @Override
    public void onUnPressedEffect(View view) {
    view.animate().scaleX(1).scaleY(1).setDuration(duration).setInterpolator(interpolator);
    }
}
