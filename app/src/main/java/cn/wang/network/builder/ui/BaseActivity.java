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


    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    protected BaseActivity thisActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(getLifecycleObserver());
        int layoutId = getLayoutId();
        setContentView(layoutId);
        init();
    }

    protected abstract int getLayoutId();

    protected abstract void init();

    public abstract LifecycleObserver getLifecycleObserver();

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("WANG","BaseActivity.onResume.");
        lifecycleRegistry.markState(Lifecycle.State.RESUMED);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("WANG","BaseActivity.onStop."+this);
        lifecycleRegistry.markState(Lifecycle.State.CREATED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("WANG","BaseActivity.onDestroy."+this);
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED);
    }
}
