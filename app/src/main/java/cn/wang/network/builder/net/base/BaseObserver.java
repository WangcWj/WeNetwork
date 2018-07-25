package cn.wang.network.builder.net.base;


import android.util.Log;

import cn.wang.network.builder.net.NetControl;
import cn.wang.network.builder.net.request.NetCallBack;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by WANG on 17/11/23.
 * 这边还要拦截 error
 */

public class BaseObserver<T> implements Observer<T> {


    private NetCallBack netCallBack;

    private NetAddDestroyDisposable mTag;

    private NetControl control;

    public BaseObserver(NetCallBack netCallBack,NetAddDestroyDisposable tag) {
        this.netCallBack = netCallBack;
        this.mTag = tag;
        control = NetControl.getInstance();
    }


    @Override
    public void onError(Throwable e) {
        netCallBack.onError(e);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {
        Log.e("WANG","BaseObserver.onSubscribe."+d.isDisposed());
       if(null != mTag){
           mTag.addDisposable(d);
       }
    }

    @Override
    public void onNext(T t) {
        netCallBack.onSuccess(t);
    }

}
