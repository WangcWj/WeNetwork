package cn.wang.network.builder.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.example.wang.networkcomponent.NetControl;
import cn.example.wang.networkcomponent.base.NetAddDestroyDisposable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by WANG on 2018/7/23.
 * 1.处理网络请求,取消订阅.
 * 2.BufferKnife,EventBus等.
 * 3.根据具体的业务去封装.
 */

public abstract class BaseActivity extends AppCompatActivity implements NetAddDestroyDisposable {

    protected CompositeDisposable mCompositeDisposable;

    protected BaseActivity thisActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean haveInit = NetControl.getInstance().isHaveInit();
        if(!haveInit){
            NetControl.getInstance().init(this.getApplicationContext());
        }
        int layoutId = getLayoutId();
        setContentView(layoutId);
        mCompositeDisposable = new CompositeDisposable();
        initView();
        initData();
        initListener();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    @Override
    public void addDisposable(Disposable disposable) {
        if (null != mCompositeDisposable && null != disposable)
            mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onDestroy() {
        if(null != mCompositeDisposable && !mCompositeDisposable.isDisposed()){
            mCompositeDisposable.dispose();
        }
        if(this instanceof MainActivity) {
            NetControl.getInstance().destroy();
        }
        super.onDestroy();
    }
}
