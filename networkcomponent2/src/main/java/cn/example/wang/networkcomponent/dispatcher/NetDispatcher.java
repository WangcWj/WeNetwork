package cn.example.wang.networkcomponent.dispatcher;

import java.util.concurrent.TimeUnit;

import cn.example.wang.networkcomponent.NetControl;
import cn.example.wang.networkcomponent.base.BaseObserver;
import cn.example.wang.networkcomponent.base.BaseParam;
import cn.example.wang.networkcomponent.base.BaseResultBean;
import cn.example.wang.networkcomponent.rxjava.NetRetryWhen;
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
