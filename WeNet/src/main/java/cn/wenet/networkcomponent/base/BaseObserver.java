package cn.wenet.networkcomponent.base;


import cn.wenet.networkcomponent.exception.NetException;
import cn.wenet.networkcomponent.request.BaseCallBack;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by WANG on 17/11/23.
 * 这边还要拦截 error
 */

public class BaseObserver<T> implements Observer<T> {


    private BaseCallBack netCallBack;

    private NetLifecycleControl mTag;

    public BaseObserver(BaseCallBack netCallBack, NetLifecycleControl tag) {
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
    public void onNext(T t) {
        if (null == netCallBack) {
            return;
        }
        if (t instanceof BaseResultBean) {
            BaseResultBean resultBean = (BaseResultBean) t;
            NetException netException = new NetException(resultBean.getCode(), resultBean.getStatus(), resultBean.getMsg());
            boolean success = netException.success();
            if (success) {
                Object data = resultBean.getData();
                if (null == data) {
                    netException.setMessage("Data数据为null!");
                    netCallBack.onError(netException);
                } else {
                    netCallBack.onSuccess(data);
                }
            } else {
                netCallBack.onError(netException);
            }
        }else if(t instanceof String){
            netCallBack.onSuccess(t);
        }
    }


}