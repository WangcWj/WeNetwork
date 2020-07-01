package cn.wang.network.builder.ui;

import android.content.Context;

import cn.wenet.networkcomponent.core.WeNetworkCallBack;
import cn.wenet.networkcomponent.debug.exception.NetException;
import io.reactivex.disposables.Disposable;

/**
 * Created to :
 *
 * @author cc.wang
 * @date 2020/7/1
 */
public class BaseNetCallback<T> implements WeNetworkCallBack<T> {

    @Override
    public void onSuccess(T t) {

    }

    @Override
    public void onError(NetException e) {

    }

    @Override
    public void pageLifeCircle(Disposable disposable) {

    }

    @Override
    public void showProgress(Context context, boolean canShow) {

    }
}
