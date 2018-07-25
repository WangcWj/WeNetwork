package cn.wang.network.builder.ui;

import android.view.View;

import java.util.Calendar;

/**
 * Created by WANG on 2018/6/28.
 * 就是过滤下点击事件
 */

public abstract class FilterClickListener implements View.OnClickListener {

    public  final int MIN_CLICK_DELAY_TIME = 300;
    private long lastClickTime = 0;

    protected abstract void mFilterClick(View v);
    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime >= MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            mFilterClick(v);
        }
    }

}
