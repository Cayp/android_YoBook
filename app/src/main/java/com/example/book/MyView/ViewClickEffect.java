package com.example.book.MyView;

import android.view.View;

/**
 * Created by ljp on 2017/9/6.
 * 点击变小动画
 */

public interface ViewClickEffect {
    void onPressedEffect(View view);
    void onUnPressedEffect(View view);

}
