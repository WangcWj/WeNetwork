package cn.wang.network.builder.ui;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by WANG on 2018/7/23.
 * 1.处理网络请求,取消订阅.
 * 2.BufferKnife,EventBus等.
 * 3.根据具体的业务去封装.
 */

public abstract class BaseActivity extends AppCompatActivity implements LifecycleOwner {



    protected BaseActivity thisActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getLayoutId();
        setContentView(layoutId);
        init();
    }

    protected abstract int getLayoutId();

    protected abstract void init();


    @Override
    protected void onResume() {
        super.onResume();
        Log.e("WANG","BaseActivity.onResume.");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("WANG","BaseActivity.onStop."+this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("WANG","BaseActivity.onDestroy."+this);

    }
}
