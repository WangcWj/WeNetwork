package cn.wang.network.builder.net.base;

import cn.wang.network.builder.net.exception.NetException;
import cn.wang.network.builder.net.request.NetCallBack;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by WANG on 17/11/23.
 * 这边还要拦截 error
 */

public class BaseObserver<T> implements Observer<BaseResultBean<T>> {


    private NetCallBack netCallBack;

    private NetAddDestroyDisposable mTag;

    public BaseObserver(NetCallBack netCallBack, NetAddDestroyDisposable tag) {
        this.netCallBack = netCallBack;
        this.mTag = tag;
    }


    @Override
    public void onError(Throwable e) {
        NetException netException = new NetException(e);
        netCallBack.onError(netException);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {
        if (null != mTag) {
            mTag.addDisposable(d);
        }
    }

    @Override
    public void onNext(BaseResultBean<T> tBaseResultBean) {
        if (null == netCallBack) return;
        NetException netException = new NetException(tBaseResultBean.getCode(), tBaseResultBean.getMsg());
        boolean success = netException.success();
        if (success) {
            netCallBack.onSuccess(tBaseResultBean.getData());
        } else {
            netCallBack.onError(netException);
        }
    }

}
