package cn.wang.network.builder.net.dispatcher;

import java.util.concurrent.TimeUnit;

import cn.wang.network.builder.net.NetControl;
import cn.wang.network.builder.net.base.BaseObserver;
import cn.wang.network.builder.net.base.BaseParam;
import cn.wang.network.builder.net.rxjava.NetRetryWhen;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by WANG on 2018/7/16.
 * 1.这里用来分发网络请求
 */

public class NetDispatcher {

    private NetControl mNetControl;

    public NetDispatcher(NetControl mNetControl) {
        this.mNetControl = mNetControl;
    }

    public <T> void toSubscribe(Observable<T> observable, BaseObserver<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(BaseParam.READ_TIMEOUT, TimeUnit.SECONDS)
                .retryWhen(new NetRetryWhen(mNetControl.getRetryWhenCount(), mNetControl.getRetryWhenTime()))
                .subscribe(observer);
    }

    public <T> T getApiService(Class<T> clz) {
        T apiService = null;
        Retrofit retrofit = mNetControl.getNetRetrofit().getRetrofit();
        if (retrofit != null) {
            apiService = retrofit.create(clz);
        }
        return apiService;
    }


}
