package cn.wenet.networkcomponent.request;


import java.util.Map;

import cn.wenet.networkcomponent.control.Control;
import cn.wenet.networkcomponent.base.NetBaseObserver;
import cn.wenet.networkcomponent.base.NetLifecycleControl;
import io.reactivex.Observable;

/**
 * @author WANG
 * @date 2018/5/4
 * 请求从这里发起.
 * 1.这里管理基础参数
 * 2.管理Header的添加
 * 3.管理文件上传RequestBody
 */

public class NetRequest {

    private Control netControl;

    private NetLifecycleControl mDestroyDisposable;

    private Observable mObservable;

    public NetRequest(Control netControl, NetLifecycleControl mDestroyDisposable) {
        this.netControl = netControl;
        this.mDestroyDisposable = mDestroyDisposable;
        netControl.clearParams();
    }

    public NetRequest addParams(String key, String value) {
        netControl.getParams().put(key, value);
        return this;
    }

    public NetRequest addParams(Map params) {
        netControl.getParams().putAll(params);
        return this;
    }

    public Observable getObservable() {
        return mObservable;
    }

    public <T> NetRequest apiMethod(Observable<T> observable) {
        mObservable = observable;
        return this;
    }

    public <T> void execute(WeNetworkCallBack<T> callback) {
        if (null == mObservable) {
            return;
        }
        //要先执行
        baseExecute(callback, mObservable);
    }

    private void baseExecute(WeNetworkCallBack callback, Observable observable) {
        NetBaseObserver baseObserver = netControl.getBaseObserve(callback, mDestroyDisposable);
        subscribe(observable, baseObserver);
    }

    private void subscribe(Observable observable, NetBaseObserver callback) {
        netControl.subscribe(observable, callback);
    }

}
